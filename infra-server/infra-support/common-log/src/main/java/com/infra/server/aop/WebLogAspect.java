package com.infra.server.aop;

import com.alibaba.fastjson.JSONObject;
import com.infra.server.annotation.InfraLog;
import com.infra.server.entities.SysLog;
import com.infra.server.mapper.SysLogMapper;
import com.infra.server.utils.IPUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.util.Date;

/**
 * @Author: zzd
 * @Date: 2020/7/3 16:44
 * @Description: 日志切面类
 */
@Aspect
@Component
public class WebLogAspect {

    private static final Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

    @Resource
    public SysLogMapper sysLogMapper;

    /**
     * 切入点  即 aop 切入的路径（被拦截的方法）
     * 匹配 controller包下的任何公共方法
    **/
    @Pointcut("execution(public * com.infra.server.controller.*.*(..))")
    public void webLog() {
    }

    /**
     * 通知：前置通知（Before advice），在连接点之前运行但不能阻止执行流继续到连接点的通知(除非它抛出异常)。
     * 在日志文件或控制台输出请求信息
     */

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        /**
         * 利用RequestContextHolder获取HttpServletRequest对象
         * 记录请求内容
        **/
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest httpServletRequest = attributes.getRequest();
        /**
         * 重组请求信息
        **/
        StringBuffer sb = new StringBuffer();
        sb.append("\r\n收到请求("+httpServletRequest.getMethod()+"):");
        sb.append("\r\nURL : " + httpServletRequest.getRequestURI().toString());
        sb.append("\r\nIP : " + IPUtil.getIpAddr(httpServletRequest));
        sb.append("\r\n响应方法 : " + joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());
        Object[] objects = joinPoint.getArgs();
        for (Object arg : objects) {
            if (arg != null) {
                sb.append("\r\n参数 : " + arg.toString());
            }
        }
        logger.info(sb.toString());
    }

    /**
     * 后置通知，通知在连接点正常完成后运行
     * 处理请求日志信息
    **/
    @AfterReturning(pointcut = "webLog()", returning = "rvt")
    public void doAfterReturning (JoinPoint joinPoint,Object rvt) {
        handleLog(joinPoint,null);
    }

    /**
     * 异常通知，方法通过抛出异常退出，则要执行的通知
     * 处理请求异常日志信息
    **/
    @AfterThrowing(pointcut = "webLog()" , throwing = "e")
    public void afterThrowing(JoinPoint joinPoint , Exception e) {
        handleLog(joinPoint,e);
    }

    /**
     * 日志处理
    **/
    private void handleLog(JoinPoint joinPoint, Exception e) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest httpServletRequest = attributes.getRequest();

        /**
         * 获取执行方法
        **/
        Signature signature = joinPoint.getSignature();
        if (!(signature instanceof MethodSignature)) {
            throw new IllegalArgumentException("暂不支持非方法注解");
        }
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        if (method != null ) {
            // 获取方法上的注解
            InfraLog infraLog = method.getAnnotation(InfraLog.class);
            if (infraLog != null ) {
                // 默认是保存日志
                if (infraLog.isSave()) {
                    SysLog sysLog = new SysLog();
                    // 暂时先给0
                    sysLog.setUserId((long) 0);
                    sysLog.setType(httpServletRequest.getMethod());
                    sysLog.setDescription(infraLog.description());
                    sysLog.setIp(IPUtil.getIpAddr(httpServletRequest));
                    sysLog.setRequestUrl(httpServletRequest.getRequestURI());
                    sysLog.setMethod(signature.getDeclaringTypeName()+"."+method.getName());
                    sysLog.setParams(JSONObject.toJSONString(httpServletRequest.getParameterMap()).replace("\"",""));
                    if (e != null) {
                        sysLog.setException(e.getMessage());
                    } else {
                      sysLog.setException(null);
                    }
                    sysLog.setCreateTime(new Date());

                    sysLogMapper.insert(sysLog);

                }
            }
        }

        if (e != null) {
            StringBuffer sb = new StringBuffer();
            sb.append("时间 : " + DateFormat.getDateTimeInstance().format(new Date()));
            sb.append("方法 : " + joinPoint.getSignature() + "\n");
            sb.append("异常信息 : " + e.getMessage());
            logger.error(sb.toString());
        }
    }
}

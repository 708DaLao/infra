package com.infra.server.constant;

import cn.hutool.core.exceptions.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ： zzd
 * @Date ： 15:08 2020/6/25
 * @Description : 统一异常处理器
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**------------通用异常处理方法------------**/
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e) {
//        e.printStackTrace();
        log.error(ExceptionUtil.getMessage(e));
        return Result.error();  //通用返回结果
    }

    /**------------指定异常处理方法-----------**/
    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public Result error(NullPointerException e) {
//        e.printStackTrace();
        log.error(ExceptionUtil.getMessage(e));
        return Result.setResult(ResultCodeEnum.NULL_POINTER);
    }

    /**------------自定义异常处理方法---------**/
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public Result error(CustomException e) {
        log.error(ExceptionUtil.getMessage(e));
        return Result.error().message(e.getMessage()).code(e.getCode());

    }
}

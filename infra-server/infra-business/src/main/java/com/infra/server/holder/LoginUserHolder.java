package com.infra.server.holder;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 获取登录用户信息
 * @author 13764
 */
@Component
public class LoginUserHolder {

    /**
     * 获取当前登录用户信息
     */
    public JSONObject getCurrentUser(){
        //从Header中获取用户信息
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String userStr = request.getHeader("userInfo");
        JSONObject obj = JSONObject.parseObject(userStr);
        return obj;
    }
}

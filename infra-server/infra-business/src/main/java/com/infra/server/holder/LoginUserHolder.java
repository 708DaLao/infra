package com.infra.server.holder;

import cn.hutool.core.convert.Convert;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 获取登录用户信息
 */
@Component
public class LoginUserHolder {

    /**
     * 获取当前登录用户信息
     */
    public String getCurrentUser(){
        //从Header中获取用户信息
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String userStr = request.getHeader("userInfo");
        System.out.println(userStr);
        JSONObject obj = new JSONObject();

//        JSONObject userJsonObject = new JSONObject(userStr);
//        UserDTO userDTO = new UserDTO();
//        userDTO.setUsername(userJsonObject.getStr("user_name"));
//        userDTO.setId(Convert.toLong(userJsonObject.get("id")));
//        userDTO.setRoles(Convert.toList(String.class,userJsonObject.get("authorities")));
        return userStr;
    }
}

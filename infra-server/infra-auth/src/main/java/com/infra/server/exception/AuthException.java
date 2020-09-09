package com.infra.server.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infra.server.constant.ResultCodeEnum;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zzd
 * @Date: 2020/9/7 9:32
 * @Description: 自定义Token异常信息, 用于token校验失败返回信息, 比如token过期/验证错误
 */
public class AuthException implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        System.out.println("自定义token异常");
        // token无效
        ResultCodeEnum resultCodeEnum = ResultCodeEnum.TOKEN_ERROR;
        Map map = new HashMap();
        map.put("code", resultCodeEnum.getCode());
        map.put("message", resultCodeEnum.getMessage());
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(response.getOutputStream(), map);
        } catch (Exception e) {
            throw new ServletException();
        }
    }
}

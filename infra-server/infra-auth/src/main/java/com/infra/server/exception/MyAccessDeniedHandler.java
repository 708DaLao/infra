//package com.infra.server.exception;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.infra.server.constant.ResultCodeEnum;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.web.access.AccessDeniedHandler;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @Author: zzd
// * @Date: 2020/9/9 16:37
// * @Description: 授权失败处理异常
// */
//public class MyAccessDeniedHandler implements AccessDeniedHandler {
//
//    @Override
//    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
//        System.out.println("授权失败处理异常");
//        ObjectMapper objectMapper = new ObjectMapper();
//        response.setContentType("application/json;charset=UTF-8");
//        ResultCodeEnum resultCodeEnum = ResultCodeEnum.NO_PERMISSION;
//        Map map = new HashMap();
//        map.put("code", resultCodeEnum.getCode());
//        map.put("message", resultCodeEnum.getMessage());
//        response.setContentType("application/json");
//        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        response.getWriter().write(objectMapper.writeValueAsString(map));
//
//    }
//}

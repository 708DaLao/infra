//package com.infra.server.exception;
//
//import com.infra.server.constant.Result;
//import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//
///**
// * 全局处理Oauth2抛出的异常
// * 默认返回格式：
// * {
// *   "error": "unauthorized",
// *   "error_description": "Full authentication is required to access this resource"
// * }
// * 自定义认证失败返回结果，使用全局Result返回
// */
//@ControllerAdvice
//public class Oauth2ExceptionHandler {
//
//    @ResponseBody
//    @ExceptionHandler(value = OAuth2Exception.class)
//    public Result handleOauth2(OAuth2Exception e) {
//        System.out.println("自定义异常"+e.getMessage());
//        return Result.error().message(e.getMessage()).code(Integer.valueOf(e.getOAuth2ErrorCode()));
//    }
//}

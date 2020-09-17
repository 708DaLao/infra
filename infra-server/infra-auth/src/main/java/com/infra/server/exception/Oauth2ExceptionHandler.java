package com.infra.server.exception;

import com.infra.server.constant.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局处理Oauth2抛出的异常
 */
@ControllerAdvice
public class Oauth2ExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Result handleOauth2(Exception e) {
        return Result.error().message(e.getMessage());
    }
}

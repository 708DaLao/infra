package com.infra.server.controller;

import com.alibaba.fastjson.JSONObject;
import com.infra.server.holder.LoginUserHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * @Author: gisocn
 * @Date: 2020/9/18
 * @Description: 系统相关模块
 **/
@RestController
@Api(value = "SystemController",tags = "系统配置相关模块")
@RequestMapping("/system")
public class SystemController {

    @Resource
    private LoginUserHolder loginUserHolder;

    @ApiOperation("获取当前登录用户")
    @GetMapping("/current_user")
    public JSONObject currentUser() {
        return loginUserHolder.getCurrentUser();
    }
}

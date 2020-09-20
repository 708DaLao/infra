package com.infra.server.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.infra.server.holder.LoginUserHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 测试接口
 * Created by macro on 2020/6/19.
 */
@RestController
public class HelloController {

    @Resource
    private LoginUserHolder loginUserHolder;

    @GetMapping("/hello")
    public String hello() {
        return "Hello World.";
    }

    @GetMapping("/user")
    public JSONObject user() {
        JSONObject object = loginUserHolder.getCurrentUser();
        System.out.println("-------------------");
        Long userId = object.getLong("user_id");
        System.out.println(userId);
        JSONArray roles = object.getJSONArray("authorities");
        System.out.println(roles);
        String username = object.getString("user_name");
        System.out.println(username);
        return loginUserHolder.getCurrentUser();
    }

}

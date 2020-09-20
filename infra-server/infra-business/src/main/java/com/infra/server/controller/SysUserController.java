package com.infra.server.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.infra.server.api.Result;
import com.infra.server.entity.SysUser;
import com.infra.server.holder.LoginUserHolder;
import com.infra.server.mapper.SysUserMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: gisocn
 * @Date: 2020/9/18
 * @Description: 系统用户模块
 **/
@RestController
@Api(value = "SysUserController",tags = "用户相关模块")
@RequestMapping("/sys_user")
public class SysUserController {

    @Resource
    private LoginUserHolder loginUserHolder;
    @Resource
    private SysUserMapper sysUserMapper;

    @ApiOperation("获取当前登录用户信息")
    @GetMapping("/info")
    public Result getInfo(){
        Map<String,Object> map = new HashMap<>();
        // 获取当前登录用户id、角色和账号
        JSONObject object = loginUserHolder.getCurrentUser();
        Long userId = object.getLong("user_id");
        JSONArray roles = object.getJSONArray("authorities");
//        String username = object.getString("user_name");
        SysUser sysUser = sysUserMapper.selectById(userId);
        map.put("userId",userId);
        map.put("roles",roles);
        map.put("info",sysUser);
        System.out.println(map);
        return Result.ok().data(map).message("获取当前登录用户信息成功");
    }


}

package com.infra.server.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.infra.server.api.Result;
import com.infra.server.entity.SysUser;
import com.infra.server.holder.LoginUserHolder;
import com.infra.server.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

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
    private SysUserService sysUserService;

    @ApiOperation("获取当前登录用户信息")
    @GetMapping("/info")
    public Result getInfo(){
        Map<String,Object> map = new HashMap<>();
        // 获取当前登录用户id、角色和账号
        JSONObject object = loginUserHolder.getCurrentUser();
        Long userId = object.getLong("user_id");
        JSONArray roles = object.getJSONArray("authorities");
//        String username = object.getString("user_name");
        SysUser sysUser = sysUserService.getById(userId);
        map.put("userId",userId);
        map.put("roles",roles);
        map.put("info",sysUser);
        return Result.ok().data(map).message("获取当前登录用户信息成功");
    }

    @ApiOperation("修改用户个人信息")
    @PostMapping("/info/update")
    public Result updateInfo(@RequestBody SysUser sysUser) {
        boolean a = sysUserService.updateById(sysUser);
        if (a) {
            SysUser user = sysUserService.getById(sysUser.getId());
            Map<String,Object> map = new HashMap<>();
            map.put("info",sysUser);
            return Result.ok().data(map).message("修改个人信息成功");
        } else {
            return Result.error().message("修改个人信息失败，请重试！");
        }
    }


}

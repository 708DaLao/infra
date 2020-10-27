package com.infra.server.controller;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.infra.server.api.Result;
import com.infra.server.entity.SysUser;
import com.infra.server.entity.SysUserAuth;
import com.infra.server.entity.vo.SysUserVo;
import com.infra.server.holder.LoginUserHolder;
import com.infra.server.service.SysUserAuthService;
import com.infra.server.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    @Resource
    private SysUserAuthService sysUserAuthService;

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

    @ApiOperation("获取用户列表")
    @GetMapping("/list")
    public Result getUserList(@RequestParam(required = false) Integer current,@RequestParam(required = false) Integer size) {
        Map<String,Object> map = new HashMap<>();
        if (current != null && size != null) {
            Page<SysUserVo> page = new Page<>(current,size);
            IPage<SysUserVo> page1 = sysUserService.getUserList(page);
            map.put("page",page1);
        }
        return Result.ok().data(map).message("获取用户列表成功");

    }

    @ApiOperation("添加或修改用户")
    @PostMapping("/save")
    public Result saveUser(@RequestBody SysUserVo sysUserVo) {
        // 用户表
        SysUser sysUser = new SysUser();
        sysUser.setAddr(sysUserVo.getAddr());
        sysUser.setBirthday(sysUserVo.getBirthday());
        sysUser.setEmail(sysUserVo.getEmail());
        sysUser.setGender(sysUserVo.getGender());
        sysUser.setPhone(sysUserVo.getPhone());
        sysUser.setRealname(sysUserVo.getRealname());

        // 鉴权表
        SysUserAuth sysUserAuth = new SysUserAuth();
        sysUserAuth.setUsername(sysUserVo.getUsername());
        sysUserAuth.setStatus(sysUserVo.getStatus());

        // 添加
        if (StrUtil.hasEmpty(Convert.toStr(sysUserVo.getId()))) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            // 初始密码设为123456
            sysUserAuth.setPassword(passwordEncoder.encode("123456"));

            QueryWrapper<SysUserAuth> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("username",sysUserAuth.getUsername());
            if (ObjectUtil.isNotNull(sysUserAuthService.getOne(queryWrapper))) {
                return Result.error().message("该账户已存在，请重新修改");
            } else {
                int a = sysUserService.addUser(sysUser,sysUserAuth);
                if (a > 0) {
                    return Result.ok().message("添加用户成功");
                } else {
                    return Result.error().message("添加用户失败");
                }
            }
        } else {
            sysUser.setId(sysUserVo.getId());
            return Result.ok().message("修用户信息成功");
        }

    }


}

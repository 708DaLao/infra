package com.infra.server.controller;

import cn.hutool.core.util.ArrayUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.infra.server.api.Result;
import com.infra.server.entity.SysRole;
import com.infra.server.mapper.SysRoleMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: zzd
 * @Date: 2020/9/19 16:57
 * @Description: 角色相关接口
 */
@RestController
@Api(value = "SysRoleController",tags = "角色相关接口")
@RequestMapping("/role")
public class SysRoleController {

    @Resource
    private SysRoleMapper sysRoleMapper;


    @ApiOperation("根据角色获取角色权限")
    @GetMapping("/permission")
    public Result getPermissionByRoles(@RequestParam Object roles) {
        System.out.println("角色：----- "+roles);
        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("name",roles);
        List<SysRole> roleList = sysRoleMapper.selectList(queryWrapper);
        System.out.println(JSONObject.toJSONString(roleList));

        return null;
    }
}

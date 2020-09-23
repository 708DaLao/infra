package com.infra.server.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.infra.server.api.Result;
import com.infra.server.entity.SysRole;
import com.infra.server.entity.SysRoleRouter;
import com.infra.server.entity.SysRouter;
import com.infra.server.mapper.SysRoleMapper;
import com.infra.server.mapper.SysRoleRouterMapper;
import com.infra.server.service.SysRoleService;
import com.infra.server.service.SysRouterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

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
    private SysRoleService sysRoleService;
    @Resource
    private SysRoleRouterMapper sysRoleRouterMapper;
    @Resource
    private SysRouterService sysRouterService;


    @ApiOperation("根据角色获取角色权限")
    @GetMapping("/permission")
    public Result getPermissionByRoles(@RequestParam String roles) {
        Object[] obj = roles.split(",");
        // 批量查询角色信息
        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("name",obj);
        List<SysRole> roleList = sysRoleService.list(queryWrapper);

        // 取出角色数组的id
        List<Integer> roleIds = roleList.stream().map(SysRole::getId).collect(Collectors.toList());
        // 批量查询路由id根据角色id
        QueryWrapper<SysRoleRouter> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.in("role_id",roleIds);
        List<SysRoleRouter> sysRoleRouterList = sysRoleRouterMapper.selectList(queryWrapper1);

        // 取出路由数组是id
        List<Integer> routeIds = sysRoleRouterList.stream().map(SysRoleRouter::getRouterId).collect(Collectors.toList());
        // 批量查询路由根据路由id
        List<SysRouter> sysRouterList = sysRouterService.listByIds(routeIds);
        System.out.println(sysRouterList);


        return null;
    }
}

package com.infra.server.controller;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.infra.server.api.Result;
import com.infra.server.entity.SysRole;
import com.infra.server.entity.SysRouter;
import com.infra.server.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;
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

    @ApiOperation("根据角色获取角色权限")
    @GetMapping("/permission")
    public Result getPermissionByRoles(@RequestParam String roles) {
        Map<String,Object> map = new HashMap<>();

        Object[] roleName = roles.split(",");
        // 批量查询角色信息
        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("name",roleName);
        List<SysRole> roleList = sysRoleService.list(queryWrapper);

        // 取出角色数组的id
        List<Integer> roleIds = roleList.stream().map(SysRole::getId).collect(Collectors.toList());
        // 根据角色id查询对应的路由列表
        List<SysRouter> sysRouterList = sysRoleService.getRouterByRoleIds(roleIds);
        // 构造路由树
        List<Object> asyncRoutes = filterRouter(sysRouterList);

        System.out.println(JSONObject.toJSONString(asyncRoutes));
        // 返回权限
        map.put("asyncRoutes",asyncRoutes);
        return Result.ok().data(map).message("获取角色权限成功！");
    }

    /**
     * 递归遍历构造路由树
     */
    public List<Object> filterRouter(List<SysRouter> sysRouterList) {
        List<Object> list = new ArrayList<>();
        for (SysRouter r : sysRouterList) {
            // 根节点父id为0
            if (r.getParentId() == 0) {
                routerData(sysRouterList, list, r);
            }
        }
        return list;
    }

    /**
     * 获取子路由
     */
    public List<Object> getChildren(Integer id,List<SysRouter> sysRouterList){
        List<Object> list = new ArrayList<>();
        for (SysRouter r : sysRouterList) {
            if (r.getParentId().equals(id)) {
                routerData(sysRouterList, list, r);
            }
        }
        return list;
    }

    /**
     * 路由结构
     */
    private void routerData(List<SysRouter> sysRouterList, List<Object> list, SysRouter r) {
        JSONObject object = new JSONObject();
        object.put("path",r.getPath());
        object.put("component",r.getComponent());
        if (!StrUtil.hasEmpty(r.getRedirect())) {
            object.put("redirect",r.getRedirect());
        }
        if (!StrUtil.hasEmpty(r.getName())) {
            object.put("name",r.getName());
        }
        Map<String,Object> meta = new HashMap<>();
        meta.put("title",r.getTitle());
        meta.put("icon",r.getIcon());
        if (r.getKeepAlive()) {
            meta.put("keepAlive",r.getKeepAlive());
        }
        if (!StrUtil.hasEmpty(r.getTitle()) || !StrUtil.hasEmpty(r.getIcon())) {
            object.put("meta", meta);
        }
        if (r.getHidden()) {
            object.put("hidden",r.getHidden());
        }
        if (r.getAlwaysShow()) {
            object.put("alwaysShow",r.getAlwaysShow());
        }
        List<Object> children = getChildren(r.getId(),sysRouterList);
        if (children.size() > 0) {
            object.put("children",children);
        }
        list.add(object);
    }

}

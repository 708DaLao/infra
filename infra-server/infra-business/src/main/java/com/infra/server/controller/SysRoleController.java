package com.infra.server.controller;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeUtil;
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
        List<Tree<String>> asyncRoutes = getRouterTree(sysRouterList);

        System.out.println(JSONObject.toJSONString(asyncRoutes));
        // 返回权限
        map.put("asyncRoutes",asyncRoutes);
        return Result.ok().data(map).message("获取角色权限成功！");
    }

    /**
     * 构建路由树
     */
    public List<Tree<String>> getRouterTree(List<SysRouter> sysRouterList) {
        // 自定义属性名，详见Hutool树工具
        // https://hutool.cn/docs/#/core/%E8%AF%AD%E8%A8%80%E7%89%B9%E6%80%A7/%E6%A0%91%E7%BB%93%E6%9E%84/%E6%A0%91%E7%BB%93%E6%9E%84%E5%B7%A5%E5%85%B7-TreeUtil
//        TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
//        treeNodeConfig.setParentIdKey("pid");
        List<Tree<String>> routers = TreeUtil.build(sysRouterList, "0",
                (router, tree) -> {
                    tree.setId(router.getId().toString());
                    tree.setParentId(router.getParentId().toString());
                    // 扩展属性 ...
                    tree.putExtra("path", router.getPath());
                    tree.putExtra("component", router.getComponent());
                    if (!StrUtil.hasEmpty(router.getRedirect())) {
                        tree.putExtra("redirect",router.getRedirect());
                    }
                    if (!StrUtil.hasEmpty(router.getName())) {
                        tree.putExtra("name",router.getName());
                    }
                    Map<String,Object> meta = new HashMap<>();
                    meta.put("title",router.getTitle());
                    meta.put("icon",router.getIcon());
                    if (router.getKeepAlive()) {
                        meta.put("keepAlive",router.getKeepAlive());
                    }
                    if (!StrUtil.hasEmpty(router.getTitle()) || !StrUtil.hasEmpty(router.getIcon())) {
                        tree.putExtra("meta", meta);
                    }
                    if (router.getHidden()) {
                        tree.putExtra("hidden",router.getHidden());
                    }
                    if (router.getAlwaysShow()) {
                        tree.putExtra("alwaysShow",router.getAlwaysShow());
                    }
                });
        return routers;
    }
}

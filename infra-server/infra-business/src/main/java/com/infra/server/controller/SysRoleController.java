package com.infra.server.controller;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
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

    @ApiOperation("根据角色获取角色权限")
    @GetMapping("/permission")
    public Result getPermissionByRoles(@RequestParam String roles) {
        String[] roleName = roles.split(",");
        // 批量查询角色信息
        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("name",roleName);
        List<SysRole> roleList = sysRoleService.list(queryWrapper);

        // 取出角色数组的id
        List<Integer> roleIds = roleList.stream().map(SysRole::getId).collect(Collectors.toList());
        // 根据角色id查询对应的路由列表
        List<SysRouter> sysRouterList = sysRoleService.getRouterByRoleIds(roleIds);

        TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
        treeNodeConfig.setParentIdKey("pid");

        List<Tree<String>> treeNodes = TreeUtil.build(sysRouterList, "0", treeNodeConfig,
                (treeNode, tree) -> {
                    tree.setId(treeNode.getId().toString());
                    tree.setParentId(treeNode.getPid().toString());
                    // 扩展属性 ...
                    tree.putExtra("path", treeNode.getPath());
                    tree.putExtra("component", treeNode.getComponent());
                });

        System.out.println(JSONObject.toJSONString(treeNodes));
        return null;
    }
}

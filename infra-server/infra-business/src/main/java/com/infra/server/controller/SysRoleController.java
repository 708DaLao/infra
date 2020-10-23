package com.infra.server.controller;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.infra.server.api.Result;
import com.infra.server.entity.SysResource;
import com.infra.server.entity.SysRole;
import com.infra.server.entity.SysRouter;
import com.infra.server.service.SysResourceService;
import com.infra.server.service.SysRoleService;
import com.infra.server.service.SysRouterService;
import com.infra.server.utils.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
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
    @Resource
    private SysRouterService sysRouterService;
    @Value("${redis.resourceKey}")
    String resourceKey;
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private SysResourceService sysResourceService;

    @ApiOperation("根据角色获取角色的动态路由")
    @GetMapping("/async_routes")
    public Result getAsyncRoutesByRoles(@RequestParam String roles) {
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

        // 返回动态路由
        map.put("asyncRoutes",asyncRoutes);
        return Result.ok().data(map).message("获取角色动态路由成功！");
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
     * 这里之所以不用hutool的树工具是为了把id和parentId两个属性移除掉，返回符合前端路由的数据结构
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

    /**
     * @param name 角色名称
     * @param size 每页大小
     * @param current 当前页
     * @Return Sysrole
     */
    @ApiOperation("获取系统角色")
    @GetMapping("/list")
    public Result getRole(@RequestParam(required = false) String name,@RequestParam(required = false) Integer current,@RequestParam(required = false) Integer size) {
        Map<String,Object> map = new HashMap<>();
        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
        if (!StrUtil.hasEmpty(name)) {
            queryWrapper.like("name",name);
        }
        if (current != null && size != null) {
            Page<SysRole> page = new Page<>(current,size);
            IPage<SysRole> list = sysRoleService.page(page,queryWrapper);
            map.put("list",list);
        } else {
            List<SysRole> list = sysRoleService.list(queryWrapper);
            map.put("list",list);
        }
        return Result.ok().data(map).message("获取角色列表成功");
    }

    @ApiOperation("添加或修改角色信息")
    @PostMapping("/save")
    public Result saveOrUpdateRole(@RequestBody SysRole sysRole) {
        if (sysRole.getId() == null) {
            QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("name",sysRole.getName());
            SysRole sysRole1 = sysRoleService.getOne(queryWrapper);
            if (ObjectUtil.isNotNull(sysRole1)) {
                return Result.error().message("该角色已存在，请重新添加！");
            } else {
                boolean a = sysRoleService.save(sysRole);
                if (a) {
                    return Result.ok().message("添加角色成功");
                } else {
                    return Result.error().message("添加角色失败，请重试!");
                }
            }
        } else {
            boolean b = sysRoleService.updateById(sysRole);
            if (b) {
                return Result.ok().message("修改角色信息成功");
            } else {
                return Result.error().message("修改角色信息失败，请重试!");
            }
        }
    }

    @ApiOperation("根据id删除角色")
    @GetMapping("/delete")
    public Result deleteRole(@RequestParam Integer id) {
        boolean a = sysRoleService.deleteRoleByRoleId(id);
        if (a) {
            return Result.ok().message("删除角色成功");
        } else {
            return Result.error().message("删除角色失败，请重试！");
        }

    }

    @ApiOperation("根据角色id获取角色权限")
    @GetMapping("/permission")
    public Result getPermissionByRoleId(@RequestParam Integer roleId) {
        Map<String,Object> map = new HashMap<>();
        // 获取路由id
        List<Object> routerIds = sysRoleService.getRouterByRoleId(roleId);
        map.put("routerIds",routerIds);
        return Result.ok().data(map).message("获取角色相关权限成功");
    }

    @ApiOperation("获取全部路由列表,且包含树结构。传分页参数则返回分页数据")
    @GetMapping("/routers/list")
    public Result getRouters(@RequestParam(required = false) Integer current,@RequestParam(required = false) Integer size) {
        Map<String,Object> map = new HashMap<>();
        if (current != null && size != null) {
            // 分页查询
            Page<SysRouter> page = new Page<>(current,size);
            IPage<SysRouter> page1 = sysRouterService.page(page);
            map.put("page",page1);
        } else {
            // 获取所有路由
            List<SysRouter> list = sysRouterService.list();
            map.put("list",list);

            // 构造路由树
            List<Tree<String>> tree = getRouterTree(list);
            map.put("tree",tree);
        }
        return Result.ok().data(map).message("获取路由成功");
    }

    /**
     * hutool树工具构建路由树
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
                    // 扩展属性
                    if (!StrUtil.hasEmpty(router.getTitle())) {
                        tree.putExtra("label",router.getTitle());
                    } else {
                        tree.putExtra("label","(无标题)");
                    }
                });
        return routers;
    }

    @ApiOperation("分配权限")
    @PostMapping("/permission/save")
    public Result savePermission(@RequestBody Map<String,Object> map) {
        int a = sysRoleService.savePermission(map);
        if (a > 0) {
            return Result.ok().message("权限分配成功");
        } else {
            return Result.error().message("权限分配失败，请重试！");
        }
    }

    @ApiOperation("保存或修改路由")
    @PostMapping("/routers/save")
    public Result saveOrUpdateRouter(@RequestBody SysRouter sysRouter) {
        boolean a = sysRouterService.saveOrUpdate(sysRouter);
        if (a) {
            return Result.ok().message("添加或修改路由成功");
        } else {
            return Result.error().message("添加或修改路由失败，请重试！");
        }
    }

    @ApiOperation("根据id删除路由")
    @GetMapping("/routers/delete")
    public Result deleteRouter(@RequestParam Integer id) {
        boolean a = sysRouterService.delRouterByRouterId(id);
        if (a) {
            return Result.ok().message("删除路由成功");
        } else {
            return Result.error().message("删除路由失败，请重试！");
        }
    }

    @ApiOperation("获取资源,即接口-角色权限")
    @GetMapping("/resource/list")
    public Result getResource(@RequestParam(required = false) String content,@RequestParam(required = false) Integer current,@RequestParam(required = false) Integer size) {
        Map<String,Object> map = new HashMap<>();
        QueryWrapper<SysResource> queryWrapper = new QueryWrapper<>();
        if (!StrUtil.hasEmpty(content)) {
            queryWrapper.like("name",content).or().like("description",content).or().like("owner",content);
        }
        if (current != null && size != null) {
            // 分页查询
            Page<SysResource> page = new Page<>(current,size);
            IPage<SysResource> page1 = sysResourceService.page(page,queryWrapper);
            map.put("page",page1);
        } else {
            // 获取所有资源
            List<SysResource> list = sysResourceService.list(queryWrapper);
            map.put("list",list);
        }
        return Result.ok().data(map).message("获取资源成功");
    }

    @ApiOperation("添加或修改资源")
    @PostMapping("/resource/save")
    public Result saveResource(@RequestBody SysResource sysResource) {
        // 添加
        if (StrUtil.hasEmpty(Convert.toStr(sysResource.getId()))) {
            QueryWrapper<SysResource> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("name",sysResource.getName());
            SysResource sysResource1 = sysResourceService.getOne(queryWrapper);
            if (ObjectUtil.isNotNull(sysResource1)) {
                return Result.error().message("该资源已存在！请重命名！");
            } else {
                boolean a = sysResourceService.save(sysResource);
                if (a) {
                    // 保存到redis
                    redisUtil.hset(resourceKey, sysResource.getName(),sysResource.getOwner().split(","));
                    return Result.ok().message("添加资源成功");
                } else {
                    return Result.error().message("添加资源失败，请重试！");
                }
            }
        } else {
            // 修改
            boolean a = sysResourceService.updateById(sysResource);
            if (a) {
                // 保存到redis,已存在则会覆盖
                redisUtil.hset(resourceKey, sysResource.getName(),sysResource.getOwner().split(","));
                return Result.ok().message("修改资源成功");
            } else {
                return Result.error().message("修改资源失败，请重试！");
            }
        }

    }

    @ApiOperation("根据id删除资源")
    @GetMapping("/resource/delete")
    public Result deleteResource(@RequestParam List<Integer> id,@RequestParam String name) {
        boolean a = sysResourceService.removeByIds(id);
        if (a) {
            // 删除redis上的资源
            redisUtil.hdel(resourceKey,name.split(","));
            return Result.ok().message("删除资源成功");
        } else {
            return Result.error().message("删除资源失败，请重试！");
        }
    }


}

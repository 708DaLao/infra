package com.infra.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.infra.server.entity.SysRole;
import com.infra.server.entity.SysRouter;

import java.util.List;
import java.util.Map;

/**
 * @Author: gisocn
 * @Date: 2020/9/23
 * @Description: 角色服务接口
 **/
public interface SysRoleService extends IService<SysRole> {

    /**
     * 根据角色id获取路由
     */
    List<SysRouter> getRouterByRoleIds(List<Integer> roleIds);

    /**
     * 根据角色id获取对应的权限路由id
     */
    List<Object> getRouterByRoleId(Integer roleId);

    /**
     * 分配路由权限
     */
    int saveRoleRouter(Integer roleId,Object routerPermission);

    /**
     * 根据角色id删除路由，同时删除其权限
     */
    boolean deleteRoleByRoleId(Integer roleId);

    /**
     * 分配权限，开启事务
     */
    int savePermission(Map<String,Object> map);



}

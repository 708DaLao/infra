package com.infra.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.infra.server.entity.SysRole;
import com.infra.server.entity.SysRouter;

import java.util.List;

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


}
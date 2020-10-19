package com.infra.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.infra.server.entity.SysRouter;

/**
 * @Author: gisocn
 * @Date: 2020/9/23
 * @Description:
 **/
public interface SysRouterService extends IService<SysRouter> {

    /**
     * 根据路由id删除路由，同时删除相关角色路由
     */
    int delRouterByRouterId(Integer routerId);
}

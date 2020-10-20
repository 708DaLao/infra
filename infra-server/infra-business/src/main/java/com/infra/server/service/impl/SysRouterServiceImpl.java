package com.infra.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.infra.server.entity.SysRouter;
import com.infra.server.mapper.SysRouterMapper;
import com.infra.server.service.SysRouterService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Author: gisocn
 * @Date: 2020/9/23
 * @Description:
 **/
@Service
public class SysRouterServiceImpl extends ServiceImpl<SysRouterMapper, SysRouter> implements SysRouterService {

    @Resource
    private SysRouterService sysRouterService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delRouterByRouterId(Integer routerId) {
        this.baseMapper.delRoleRouterByRouterId(routerId);
        return sysRouterService.removeById(routerId);
    }
}

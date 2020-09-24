package com.infra.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.infra.server.entity.SysRole;
import com.infra.server.entity.SysRouter;
import com.infra.server.mapper.SysRoleMapper;
import com.infra.server.service.SysRoleService;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @Author: gisocn
 * @Date: 2020/9/23
 * @Description: 角色服务实现类
 **/
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Override
    public List<SysRouter> getRouterByRoleIds(List<Integer> roleIds) {
        return this.baseMapper.getRouterByRoleIds(roleIds);
    }
}

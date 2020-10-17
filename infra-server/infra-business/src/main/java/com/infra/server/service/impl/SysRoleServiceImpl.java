package com.infra.server.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.infra.server.entity.SysRole;
import com.infra.server.entity.SysRouter;
import com.infra.server.mapper.SysRoleMapper;
import com.infra.server.service.SysRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public List<Object> getRouterByRoleId(Integer roleId) {
        return this.baseMapper.getRouterByRoleId(roleId);
    }

    /**
     * 需要开启事务，先删除旧的权限，再添加新权限
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int saveRoleRouter(Integer roleId, Object routerPermission) {
        if (ObjectUtil.isNotEmpty(routerPermission)) {
            this.baseMapper.delRoleRouterByRoleId(roleId);
            return this.baseMapper.saveRoleRouter(routerPermission);
        } else {
            return this.baseMapper.delRoleRouterByRoleId(roleId);
        }
    }

}

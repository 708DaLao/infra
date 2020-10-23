package com.infra.server.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.infra.server.entity.SysResource;
import com.infra.server.entity.SysRole;
import com.infra.server.entity.SysRouter;
import com.infra.server.mapper.SysRoleMapper;
import com.infra.server.service.SysResourceService;
import com.infra.server.service.SysRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * @Author: gisocn
 * @Date: 2020/9/23
 * @Description: 角色服务实现类
 **/
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Resource
    private SysRoleService sysRoleService;
    @Resource
    private SysResourceService sysResourceService;

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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteRoleByRoleId(Integer roleId) {
        this.baseMapper.delRoleRouterByRoleId(roleId);
        return sysRoleService.removeById(roleId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int savePermission(Map<String, Object> map) {
        int a = 0;
        // 路由权限
        Integer roleId = (Integer) map.get("roleId");
        Object routerPermission = map.get("routerPermission");
        a = sysRoleService.saveRoleRouter(roleId,routerPermission);
        // 资源权限
        List<SysResource> resourcePermission = (List<SysResource>) map.get("resourcePermission");
        boolean b = sysResourceService.updateBatchById(resourcePermission);
        if (b) {
            a = 1;
        } else {
            a = 0;
        }


        return a;
    }

}

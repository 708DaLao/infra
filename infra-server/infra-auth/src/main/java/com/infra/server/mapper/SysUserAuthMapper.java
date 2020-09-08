package com.infra.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.infra.server.entities.SysRole;
import com.infra.server.entities.SysRouter;
import com.infra.server.entities.SysUserAuth;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: zzd
 * @Date: 2020/9/7 9:19
 * @Description:
 */
public interface SysUserAuthMapper extends BaseMapper<SysUserAuth> {

    /**
     * 根据用户id获取角色
     */
    @Select("select * from sys_role where id in (select role_id from sys_user_role where user_id = #{userId})")
    List<SysRole> getRoleByUserId(Long userId);

    /**
     * 根据角色id获取菜单路由
     */
    @Select("select * from sys_router where id in (select routerId from sys_role_router where roleId = #{roleId})")
    List<SysRouter> getRouterByRoleId(Integer roleId);
}

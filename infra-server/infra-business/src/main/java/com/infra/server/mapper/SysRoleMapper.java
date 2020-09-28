package com.infra.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.infra.server.entity.SysRole;
import com.infra.server.entity.SysRouter;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: zzd
 * @Date: 2020/9/19 18:07
 * @Description: 角色
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 根据角色id查询路由
     */
    @Select("<script> " +
            "select sr.* from sys_role_router srr " +
            "left join sys_role r on r.id = srr.role_id " +
            "left join sys_router sr on sr.id = srr.router_id where srr.role_id in" +
            "<foreach collection='roleIds' item='roleId' separator=',' open='(' close=')' >" +
            "#{roleId}" +
            "</foreach> order by sr.sort" +
            "</script>")
    List<SysRouter> getRouterByRoleIds(@Param("roleIds") List<Integer> roleIds);

}
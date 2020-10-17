package com.infra.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.infra.server.entity.SysRole;
import com.infra.server.entity.SysRouter;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
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
     * select使用distinct避免返回重复数据
     */
    @Select("<script> " +
            "select distinct sr.* from sys_role_router srr " +
            "left join sys_role r on r.id = srr.role_id " +
            "left join sys_router sr on sr.id = srr.router_id where srr.role_id in" +
            "<foreach collection='roleIds' item='roleId' separator=',' open='(' close=')' >" +
            "#{roleId}" +
            "</foreach> order by sr.sort" +
            "</script>")
    List<SysRouter> getRouterByRoleIds(@Param("roleIds") List<Integer> roleIds);

    /**
     * 根据角色id获取对应的权限路由id
     */
    @Select("select srr.router_id from sys_role_router srr where srr.role_id = #{roleId}")
    List<Object> getRouterByRoleId(@Param("roleId") Integer roleId);

    /**
     * 根据角色id删除角色路由
     */
    @Delete("delete from sys_role_router srr where srr.role_id = #{roleId}")
    int delRoleRouterByRoleId(@Param("roleId") Integer roleId);

    /**
     * 批量插入角色路由
     */
    @Insert("<script> " +
            "insert into sys_role_router(role_id,router_id) values" +
            "<foreach collection='routerPermission' item='item' index='index' separator=','>" +
            "(#{item.roleId},#{item.routerId})" +
            "</foreach>" +
            "</script>")
    int saveRoleRouter(@Param("routerPermission") Object routerPermission);

}

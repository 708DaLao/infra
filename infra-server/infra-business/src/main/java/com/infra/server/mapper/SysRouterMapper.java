package com.infra.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.infra.server.entity.SysRouter;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: gisocn
 * @Date: 2020/9/23
 * @Description:
 **/
public interface SysRouterMapper extends BaseMapper<SysRouter> {

    /**
     * 根据路由id删除
     */
    @Delete("delete from sys_role_router srr where srr.router_id = #{routerId}")
    int delRoleRouterByRouterId(@Param("routerId") Integer routerId);
}

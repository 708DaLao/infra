package com.infra.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.infra.server.entity.SysUser;
import com.infra.server.entity.vo.SysUserVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: zzd
 * @Date: 2020/9/19 16:19
 * @Description: 用户具体详情
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 获取用户列表信息
     */
    @Select("select su.*,sua.* from sys_user su,sys_user_auth sua where su.id = sua.user_id")
    IPage<SysUserVo> getUserList(@Param("page") IPage<SysUserVo> page);
}

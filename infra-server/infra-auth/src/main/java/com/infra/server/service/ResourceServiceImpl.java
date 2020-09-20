package com.infra.server.service;

import com.infra.server.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 资源与角色匹配关系管理业务类
 * @author zzd
 */
@Service
public class ResourceServiceImpl {

    @Resource
    @Qualifier("redisTemplate")//指定导入的这个redisTemplate是我们重写的,默认是源码中的
    private RedisTemplate redisTemplate;
    @Resource
    private RedisUtil redisUtil;

    public static final String RESOURCE_ROLES_MAP = "AUTH:RESOURCE_ROLES_MAP";

    @PostConstruct
    public void initData() {
        Map<String, Object> resourceRolesMap = new HashMap<>();
        resourceRolesMap.put("/api/hello","ADMIN".split(","));
        resourceRolesMap.put("/api/user", "ADMIN,TEST".split(","));
        resourceRolesMap.put("/api/system/current_user", "ADMIN,TEST".split(","));
        resourceRolesMap.put("/api/sys_user/info", "ADMIN,TEST".split(","));
        resourceRolesMap.put("/api/role/permission", "ADMIN,TEST".split(","));
        redisUtil.hmset(RESOURCE_ROLES_MAP, resourceRolesMap);
    }
}

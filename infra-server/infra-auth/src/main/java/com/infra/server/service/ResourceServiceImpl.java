package com.infra.server.service;

import com.infra.server.utils.RedisUtil;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;
import java.util.TreeMap;

/**
 * 资源与角色匹配关系管理业务类
 * @author zzd
 */
@Service
public class ResourceServiceImpl {

    @Resource
    private RedisUtil redisUtil;

    public static final String RESOURCE_ROLES_MAP = "AUTH:RESOURCE_ROLES_MAP";

    @PostConstruct
    public void initData() {
        Map<String, Object> resourceRolesMap = new TreeMap<>();
        resourceRolesMap.put("/api/hello","ADMIN".split(","));
        resourceRolesMap.put("/api/user", "ADMIN,TEST".split(","));
        redisUtil.hmset(RESOURCE_ROLES_MAP, resourceRolesMap);
    }
}

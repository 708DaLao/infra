package com.infra.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.infra.server.entity.SysResource;
import com.infra.server.mapper.SysResourceMapper;
import com.infra.server.service.SysResourceService;
import org.springframework.stereotype.Service;

/**
 * @Author: gisocn
 * @Date: 2020/10/21
 * @Description:
 **/
@Service
public class SysResourceServiceImpl extends ServiceImpl<SysResourceMapper, SysResource> implements SysResourceService {
}

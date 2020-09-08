package com.infra.server.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.infra.server.constant.CustomException;
import com.infra.server.constant.ResultCodeEnum;
import com.infra.server.entities.OauthClientDetails;
import com.infra.server.mapper.OauthClientDetailsMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * @Author: zzd
 * @Date: 2020/9/7 9:12
 * @Description: 客户端详情信息，客户端详情信息在这里进行初始化，通过数据库来存储调取详情信息
 * ClientDetailsServiceImpl和SysUserServiceImpl
 * 用户登录时（即携带参数请求/oauth/token接口）会调用这两个service。
 * ClientDetailsServiceImpl是根据client_id查出来的信息验证用户登录时携带的参数（即客户端详情表信息）是否正确。并且设置能访问的资源id集合。
 * SysUserServiceImpl根据用户名去查用户密码，用户所拥有的角色等信息，然后丢给security去验证用户登录时的用户名和密码是否正确

 */
@Service
public class ClientDetailsServiceImpl implements ClientDetailsService {

    @Resource
    private OauthClientDetailsMapper oauthClientDetailsMapper;


    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        QueryWrapper<OauthClientDetails> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("client_id",clientId);
        // 根据客户端id获取客户端信息
        OauthClientDetails model = oauthClientDetailsMapper.selectOne(queryWrapper);
        if (model == null ) {
            throw new CustomException(ResultCodeEnum.NULL_CLIENTID);
        }

        BaseClientDetails clientDetails = new BaseClientDetails();
        //客户端(client)id
        clientDetails.setClientId(model.getClientId());
        //客户端所能访问的资源id集合
        String resourceIds = model.getResourceIds();
        if (resourceIds != null ) {
            clientDetails.setResourceIds(Arrays.asList(resourceIds.split(",")));
        }
        //客户端(client)的访问密匙
        clientDetails.setClientSecret(model.getClientSecret());
        //客户端支持的grant_type授权类型
        clientDetails.setAuthorizedGrantTypes(Arrays.asList(model.getAuthorizedGrantTypes().split(",")));
        //客户端申请的权限范围
        clientDetails.setScope(Arrays.asList(model.getScope().split(",")));
        Integer accessTokenValidity = model.getAccessTokenValidity();
        if (accessTokenValidity != null && accessTokenValidity > 0) {
            //设置token的有效期，不设置默认12小时
            clientDetails.setAccessTokenValiditySeconds(accessTokenValidity);
        }
        Integer refreshTokenValidity = model.getRefreshTokenValidity();
        if (refreshTokenValidity != null && refreshTokenValidity > 0) {
            //设置刷新token的有效期，不设置默认30天
            clientDetails.setRefreshTokenValiditySeconds(refreshTokenValidity);
        }
        System.out.println("客户端信息: " + model);
        return clientDetails;
    }
}

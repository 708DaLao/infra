package com.infra.server.controller;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

/**
 * @Author: zzd
 * @Date: 2020/9/9 14:17
 * @Description: 认证授权中心接口
 */
@RestController
@Api(value = "AuthController",tags = "认证授权中心接口")
public class AuthController {

    @Resource
    private KeyPair keyPair;

    @ApiOperation("获取RSA公钥接口")
    @GetMapping("/rsa/public_key")
    public Map<String, Object> getKey() {
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAKey key = new RSAKey.Builder(publicKey).build();
        return new JWKSet(key).toJSONObject();
    }
}

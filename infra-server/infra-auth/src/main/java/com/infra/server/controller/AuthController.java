package com.infra.server.controller;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

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
//    @Resource
//    private TokenEndpoint tokenEndpoint;

    @ApiOperation("获取RSA公钥接口")
    @GetMapping("/rsa/public_key")
    public Map<String, Object> getKey() {
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAKey key = new RSAKey.Builder(publicKey).build();
        return new JWKSet(key).toJSONObject();
    }


//    @ApiOperation("自定义Oauth2获取令牌接口")
//    @PostMapping("/oauth/token")
//    public Result postAccessToken(Principal principal, @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
//        OAuth2AccessToken oAuth2AccessToken = tokenEndpoint.postAccessToken(principal, parameters).getBody();
//        Oauth2TokenDto oauth2TokenDto = Oauth2TokenDto.builder()
//                .token(oAuth2AccessToken.getValue())
//                .refreshToken(oAuth2AccessToken.getRefreshToken().getValue())
//                .expiresIn(oAuth2AccessToken.getExpiresIn())
//                .tokenHead("Bearer ").build();
//
//        return Result.ok().data("Access_token",oauth2TokenDto).message("获取token成功！");
//    }

}

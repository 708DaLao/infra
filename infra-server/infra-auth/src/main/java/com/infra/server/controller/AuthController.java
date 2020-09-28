package com.infra.server.controller;

import com.infra.server.api.Result;
import com.infra.server.api.ResultCodeEnum;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.security.KeyPair;
import java.security.Principal;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
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
    @Resource
    private TokenEndpoint tokenEndpoint;
    @Resource
    private ConsumerTokenServices consumerTokenServices;

    @ApiOperation("获取RSA公钥接口")
    @GetMapping("/rsa/public_key")
    public Map<String, Object> getKey() {
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAKey key = new RSAKey.Builder(publicKey).build();
        return new JWKSet(key).toJSONObject();
    }


    /**
     * 自定义返回格式
     */
    @ApiOperation("自定义Oauth2获取令牌接口返回格式")
    @PostMapping("/oauth/token")
    public Result postAccessToken(Principal principal, @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
        OAuth2AccessToken accessToken = tokenEndpoint.postAccessToken(principal, parameters).getBody();
        Map<String,Object> map = new HashMap<>();
        map.put("access_token", accessToken.getValue());
        if (accessToken.getRefreshToken() != null) {
            map.put("refresh_token",accessToken.getRefreshToken().getValue());
        }
        map.put("expires_in",accessToken.getExpiresIn());
        return Result.ok().data(map).message("获取token成功!");
    }

    @ApiOperation("退出登录")
    @GetMapping("/logout")
    public Result logout(@RequestParam String token){
        boolean a = consumerTokenServices.revokeToken(token);
        if (a) {
            return Result.ok().message("退出成功");
        } else {
            return Result.setResult(ResultCodeEnum.TOKEN_ERROR);
        }
    }

}

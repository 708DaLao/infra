package com.infra.server.controller;

import com.infra.server.annotation.InfraLog;
import com.infra.server.constant.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zzd
 * @Date: 2020/7/4 19:47
 * @Description: 测试类
 */
@RestController
public class testController {

    @InfraLog(description = "测试111")
    @GetMapping(value = "/test")
    public Result test() {
        return Result.ok().code(200).message("测试");
    }
}

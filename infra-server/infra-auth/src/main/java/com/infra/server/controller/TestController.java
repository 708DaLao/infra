package com.infra.server.controller;

import com.infra.server.constant.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/getName")
    public Result getName() {
        String name = "王帅逼";
        return Result.ok().message(name);
    }
}

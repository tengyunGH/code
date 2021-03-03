package com.tengyun.quartzdemo.controller;

import org.redisson.Redisson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tengyun
 * @date 2020/12/16 14:31
 **/
@RestController
public class TestController {

    @Autowired
    private Redisson redisson;

    @GetMapping("/test")
    public void test() {
        redisson.getLock("test");
    }

}

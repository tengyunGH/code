package com.tengyun.quartzdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tengyun
 * @date 2020/12/16 14:31
 **/
@RestController
public class TestController {

    @GetMapping("/test")
    public void test() {

    }

}

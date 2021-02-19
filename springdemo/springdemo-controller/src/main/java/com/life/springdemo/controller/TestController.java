package com.life.springdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tengyun
 * @date 2021/2/19 10:13
 **/
@RestController
public class TestController {

    @GetMapping("/test")
    public void test() {
        System.out.println("test");
    }

}

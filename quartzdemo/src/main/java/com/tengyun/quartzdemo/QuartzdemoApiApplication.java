package com.tengyun.quartzdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * 使用定时器实现
 * @author tengyun
 * @date 2020/12/16 14:26
 **/
@SpringBootApplication
@EnableConfigurationProperties
public class QuartzdemoApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuartzdemoApiApplication.class, args);
    }

}

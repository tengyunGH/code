package com.tengyun.redis.verifytest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author tengyun
 * @date 2021/1/25 13:44
 **/
@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
@EnableConfigurationProperties
public class RedisTestApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(RedisTestApplication.class, args);
    }


}

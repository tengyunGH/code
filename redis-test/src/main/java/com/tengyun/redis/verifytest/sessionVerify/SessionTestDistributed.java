package com.tengyun.redis.verifytest.sessionVerify;

import com.tengyun.redis.verifytest.common.RedisSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author tengyun
 * @date 2021/1/31 15:15
 **/
@RestController
public class SessionTestDistributed {

    private String redisPrefix = "code:redistest:login:";

    Logger logger = LoggerFactory.getLogger(com.tengyun.redis.verifytest.sessionVerify.SessionTest.class);

    @Resource
    private RedisSupport redisSupport;

    @GetMapping("/login/session/distributed")
    public Map<String, Object> test(HttpServletRequest request, HttpServletResponse response) {
        // 1、验证登录名和密码
        String userName = "tengxyun";
        long userId = 123L;
        // 2、验证通过后,将sessionId存储到redis中，每次访问接口都获取并判断redis中是否存在
        String id = request.getSession().getId();
        redisSupport.set(redisPrefix+id, id);
        Map<String, Object> params = new HashMap<>();
        params.put("code", 0);
        return params;
    }

    @GetMapping("/data/session/distributed")
    public Boolean getData(HttpServletRequest request) {
        return true;
    }

}

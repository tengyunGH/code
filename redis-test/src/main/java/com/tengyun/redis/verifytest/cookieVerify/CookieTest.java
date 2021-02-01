package com.tengyun.redis.verifytest.cookieVerify;

import com.tengyun.redis.verifytest.common.RedisSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author tengyun
 * @date 2021/1/28 11:21
 **/
@RestController
public class CookieTest {

    private String redisPrefix = "code:redistest:login:";

    Logger logger = LoggerFactory.getLogger(com.tengyun.redis.verifytest.sessionVerify.SessionTest.class);

    @Resource
    private RedisSupport redisSupport;

    @GetMapping("/login/cookie")
    public Map<String, Object> test(HttpServletRequest request, HttpServletResponse response) {
        // 1、验证登录名和密码
        String userName = "tengxyun";
        long userId = 123L;

        // 2、验证通过后,将有用的东西写入session中，将sessionId写入cookie，将cookie塞入请求头中，返回
        String id = request.getSession().getId();
        redisSupport.set(redisPrefix+id, Long.toString(userId));
        Cookie cookie = new Cookie("sessionId", id);
        response.addCookie(cookie);
        Map<String, Object> params = new HashMap<>();
        params.put("code", 0);
        return params;
    }

    @GetMapping("/data/cookie")
    public Boolean getData(HttpServletRequest request) {
        return true;
    }

}
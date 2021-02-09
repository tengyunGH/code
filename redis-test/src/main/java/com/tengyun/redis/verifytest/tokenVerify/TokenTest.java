package com.tengyun.redis.verifytest.tokenVerify;

import com.tengyun.redis.verifytest.common.AesSupport;
import com.tengyun.redis.verifytest.common.RedisSupport;
import com.tengyun.redis.verifytest.common.UserAgentUtil;
import com.tengyun.redis.verifytest.common.UserClient;
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
 * @date 2021/1/31 15:28
 **/
@RestController
public class TokenTest {

    @Resource
    private AesSupport aesSupport;

    private String redisPrefix = "code:redistest:login:";

    Logger logger = LoggerFactory.getLogger(com.tengyun.redis.verifytest.sessionVerify.SessionTest.class);

    @Resource
    private RedisSupport redisSupport;

    @GetMapping("/login/token")
    public Map<String, Object> test(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 1、验证登录名和密码
        String userName = "tengxyun";
        long userId = 123L;
        // 2、验证通过后,使用用户Id、user-agent进行加密生成一个token，放在cookie里
        UserClient userAgent = UserAgentUtil.getUserClient(request.getHeader("user-agent"));
        String secret = aesSupport.encrypt(userId + "^" + userAgent.getClientId() + "^" + userAgent.getClientType());
        Cookie cookie = new Cookie("ut", secret);
        cookie.setPath("/");
        response.addCookie(cookie);
        Map<String, Object> params = new HashMap<>();
        params.put("code", 0);
        return params;
    }

    @GetMapping("/data/tok/token")
    public Boolean getData(HttpServletRequest request) {
        System.out.println("controller");
        return true;
    }

}

package com.tengyun.redis.verifytest.common.filter;

import com.tengyun.redis.verifytest.common.AesSupport;
import com.tengyun.redis.verifytest.common.RedisSupport;
import com.tengyun.redis.verifytest.common.UserAgentUtil;
import com.tengyun.redis.verifytest.common.UserClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author tengyun
 * @date 2021/1/31 14:02
 **/
@Component
public class AuthFilter implements Filter {

    @Resource
    private RedisSupport redisSupport;

    @Resource
    private AesSupport aesSupport;

    private String redisPrefix = "code:redistest:login:";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest servletRequest1 = (HttpServletRequest)servletRequest;
        /*if ("/login/cookie".equals(servletRequest1.getServletPath())) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            // 从cookie中获取
            Cookie[] cookies = servletRequest1.getCookies();
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                if ("sessionId".equals(name)) {
                    String sessionId = cookie.getValue();
                    if (redisSupport.hasKey(redisPrefix+sessionId)) {
                        filterChain.doFilter(servletRequest, servletResponse);
                    }
                }
            }*/
        /*if ("/login/session".equals(servletRequest1.getServletPath())) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            Object isLogin1 = servletRequest1.getSession().getAttribute("isLogin");
            if (isLogin1 != null && "1".equals(isLogin1.toString())) {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }*/
        /*if ("/login/session/distributed".equals(servletRequest1.getServletPath())) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            String id = servletRequest1.getSession().getId();
            if (redisSupport.hasKey(redisPrefix+id)) {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }*/
        if ("/login/token".equals(servletRequest1.getServletPath())) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            // 从cookie中获取token
            Cookie[] cookies = servletRequest1.getCookies();
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                if ("ut".equals(name)) {
                    String token = cookie.getValue();
                    // 验证token的正确性
                    try {
                        String decrypt = aesSupport.decrypt(token);
                        String[] split = decrypt.split("\\^");
                        UserClient userAgent = UserAgentUtil.getUserClient(servletRequest1.getHeader("user-agent"));
                        if (userAgent.getClientId().equals(split[1]) && userAgent.getClientType().equals(split[2])) {
                            servletRequest1.setAttribute("userId", split[0]);
                            filterChain.doFilter(servletRequest, servletResponse);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    @Override
    public void destroy() {

    }
}

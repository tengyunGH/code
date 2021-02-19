package com.life.springdemo.common.security.console;

import org.springframework.beans.factory.annotation.Value;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author tengyun
 * @date 2021/2/19 17:42
 **/
@WebFilter
public class TestFilter implements Filter {

    @Value("${security.token.check.openCheck}")
    private String openCheck;

    public TestFilter() {
        System.out.println("我被创建了");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("TestFilter ++++++++++++++++++++++++++" + openCheck);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}

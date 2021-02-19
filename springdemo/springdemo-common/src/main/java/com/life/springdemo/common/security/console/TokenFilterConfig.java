package com.life.springdemo.common.security.console;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tengyun
 * @date 2021/2/19 11:20
 **/
@Configuration
public class TokenFilterConfig {

    @Value("${security.token.check.openCheck}")
    private String test;

    @Bean
    public FilterRegistrationBean<TokenFilter> tokenFilterRegistration(TokenFilterParam param) {
        FilterRegistrationBean<TokenFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new TokenFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("openCheck", param.getOpenCheck());
        registration.addInitParameter("checkUrl", param.getUrl());
        registration.addInitParameter("except", param.getExcept());
        registration.addInitParameter("ignoreRefer", param.getIgnoreRefer());
        registration.addInitParameter("login", param.getLogin());
        registration.addInitParameter("test", test);
        registration.setName("TokenFilter");
        registration.setOrder(0);
        return registration;
    }

}

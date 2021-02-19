package com.life.springdemo.common.security.console;

import com.life.springdemo.common.http.HttpResult;
import com.life.springdemo.common.http.OkHttpUtil;
import com.life.springdemo.utils.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tengyun
 * @date 2021/2/19 11:21
 **/
public class TokenFilter implements Filter {

    public TokenFilter() {
        System.out.println("TokenFilter被创建了");
    }

    @Resource
    private OkHttpUtil okHttpUtil;

    private final Logger logger = LoggerFactory.getLogger(TokenFilter.class);
    /**
     * 是否开启校验
     **/
    private Boolean openCheck = true;
    /**
     * 校验服务的地址
     **/
    private String checkUrl;
    /**
     * 排除校验的list
     **/
    private List<String> exceptList = new ArrayList<>();
    /**
     * 忽略校验的页面地址
     **/
    private List<String> ignoreReferList = new ArrayList<>();
    /**
     * 校验失败后返回的登录地址
     **/
    private String login;

    private String openCheck2;

    @Override
    public void init(FilterConfig config) {
        openCheck2 = config.getInitParameter("test");
        System.out.println("123432`     " + openCheck2);
        this.checkUrl = config.getInitParameter("checkUrl");
        this.login = config.getInitParameter("login");
        String exceptStr = config.getInitParameter("except");
        if (exceptStr != null && !"".equals(exceptStr.trim())) {
            String[] array = exceptStr.split(";");
            this.exceptList = Arrays.asList(array);
        }

        String ignoreReferStr = config.getInitParameter("ignoreRefer");
        if (ignoreReferStr != null && !"".equals(ignoreReferStr.trim())) {
            String[] array = ignoreReferStr.split(";");
            this.ignoreReferList = Arrays.asList(array);
        }

        String openCheckStr = config.getInitParameter("openCheck");
        if (openCheckStr != null && !"".equals(openCheckStr.trim())) {
            this.openCheck = Boolean.valueOf(openCheckStr);
        }

        //初始化filter时手动注入bean对象
        ServletContext context = config.getServletContext();
        ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(context);
        assert ac != null;
        okHttpUtil = ac.getBean(OkHttpUtil.class);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("tokenFilter ++++++++++++++++++++++++++" + openCheck2);
        chain.doFilter(request, response);
        /*HttpServletRequest httpRequest = (HttpServletRequest) request;
        String url = httpRequest.getHeader("referer");
        String uri = httpRequest.getRequestURI();
        String ip = getIp(httpRequest);
        String userAgent = httpRequest.getHeader("user-agent");
        this.logger.debug("check referer ->{}, uri->{}, ip->{}, userAgent->{}", url, uri, ip, userAgent);
        String method = httpRequest.getMethod();
        // 请求方法为OPTIONS表示允许客户端查看服务器的性能   查看是否开启检查    是否忽略检查这个uri     是否排除这个uri的检查
        if ("OPTIONS".equalsIgnoreCase(method) || !this.openCheck || this.exceptList.contains(uri)) {
            chain.doFilter(request, response);
        } else {
            Boolean pass = false;
            // 从cookie中获取_ut
            String ut = this.getUt(httpRequest);
            if (ut != null && !"".equals(ut.trim())) {
                boolean ignoreRefer = this.ignoreReferList.contains(uri);
                // 检查是否通过
                pass = check(httpRequest, ut, url, ignoreRefer, ip, userAgent);
            }
            if (pass) {
                chain.doFilter(request, response);
            } else {
                this.logger.debug("check ut error ->{}, uri->{}", url, uri);
                response.setContentType("application/json;charset=utf-8");
                Map<String, Object> result = new HashMap<>();
                result.put("code", "9999");
                result.put("msg", "未登录或已失效，请重新登录！");
                result.put("data", this.login);
                response.getWriter().write(JsonUtil.javaBeanToString(result));
                response.getWriter().flush();
            }
        }*/
    }

    private Boolean check(HttpServletRequest httpRequest, String ut, String url, Boolean ignoreRefer, String ip, String userAgent) {
        boolean pass = false;
        Map<String, Object> params = new HashMap<>();
        params.put("token", ut);
        params.put("url", url);
        params.put("ip", ip);
        params.put("ignoreRefer", ignoreRefer);
        params.put("userAgent", userAgent);
        try {
            // 调用检查服务检验登录是否有效
            HttpResult httpResult = okHttpUtil.post(this.checkUrl, JsonUtil.javaBeanToString(params));
            if (httpResult.isSuccessful()) {
                Map<String, Object> checkResult = (Map<String, Object>) JsonUtil.stringToJavaBean(httpResult.getBody().toString(), Map.class);
                String checkPass = String.valueOf(checkResult.get("pass"));
                if ("true".equals(checkPass)) {
                    String userId = String.valueOf(checkResult.get("userId"));
                    String userName = String.valueOf(checkResult.get("userName"));
                    String workId = String.valueOf(checkResult.get("workId"));
                    httpRequest.setAttribute("userId", userId);
                    httpRequest.setAttribute("userName", userName);
                    httpRequest.setAttribute("workId", workId);
                    pass = true;
                }
            }
        } catch (Exception var15) {
            this.logger.error("doFilter error", var15);
        }
        return pass;
    }

    private String getUt(HttpServletRequest httpRequest) {
        Cookie[] cookies = httpRequest.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("_ut".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    private static String getIp(HttpServletRequest httpServletRequest) {
        String ip = httpServletRequest.getHeader("X-Forwarded-For");
        if (ip != null && !"".equals(ip.trim())) {
            int index = ip.indexOf(",");
            if (index != -1) {
                ip = ip.substring(0, index);
            }
            return ip;
        } else {
            ip = httpServletRequest.getHeader("X-Real-IP");
            if (ip == null || "".equals(ip.trim())) {
                ip = httpServletRequest.getRemoteAddr();
            }
            return ip;
        }
    }

    @Override
    public void destroy() {

    }
}

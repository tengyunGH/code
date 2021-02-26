package com.life.common.security.console;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author tengyun
 * @date 2021/2/19 11:13
 **/
@Component
@ConfigurationProperties(prefix = "security.token.check")
public class TokenFilterParam {

    /**
     * 是否开启检查
     */
    private String openCheck;
    /**
     *
     */
    private String url;
    /**
     *
     */
    private String except = "";
    /**
     *
     */
    private String ignoreRefer = "";
    /**
     *
     */
    private String login;

    public String getOpenCheck() {
        return openCheck;
    }

    public void setOpenCheck(String openCheck) {
        this.openCheck = openCheck;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getExcept() {
        return except;
    }

    public void setExcept(String except) {
        this.except = except;
    }

    public String getIgnoreRefer() {
        return ignoreRefer;
    }

    public void setIgnoreRefer(String ignoreRefer) {
        this.ignoreRefer = ignoreRefer;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}

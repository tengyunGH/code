package com.tengyun.redis.verifytest.common;

import eu.bitwalker.useragentutils.UserAgent;

/**
 * @author tengyun
 * @date 2021/1/31 17:05
 **/
public class UserAgentUtil {

    public static UserClient getUserClient(String agent) {
        UserAgent userAgent = UserAgent.parseUserAgentString(agent);
        String clientId = String.valueOf(userAgent.getId());
        String clientType;
        if(agent.contains("MicroMessenger")) {
            clientType = "wechat";
        } else if(agent.contains("Android")){
            clientType = "android";
        } else if(agent.contains("iPhone")){
            clientType = "ios";
        } else {
            clientType = "other";
        }
        return new UserClient(clientType, clientId);
    }

}

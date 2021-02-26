package com.life.mysql.common.http;


import com.life.mysql.common.constants.ErrorEnums;
import com.life.mysql.common.exception.BusinessException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * http工具类
 * @author tengyun
 * @date 2020/12/18 9:03
 **/
@Component
public class OkHttpUtil {

    private static final Logger logger = LoggerFactory.getLogger(OkHttpUtil.class);

    @Resource
    private OkHttpClient okHttpClient;

    public HttpResult get(String url, Map<String, Object> queries) {
        StringBuilder sb = new StringBuilder(url);
        if (queries != null) {
            sb.append("?");
            Set<String> keys = queries.keySet();
            for (String s : keys) {
                sb.append(s).append("=").append(queries.get(s).toString()).append("&");
            }
        }
        Request request = new Request.Builder().url(sb.toString()).build();
        return exec(request);
    }


    public HttpResult post(Request.Builder request, String url, String jsonParams) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonParams);
        Request postRequest = request.url(url).post(requestBody).build();
        return exec(postRequest);
    }

    public HttpResult post(String url, String jsonParams) {
        return post(new Request.Builder(), url, jsonParams);
    }


    public HttpResult exec(Request request) {
        Response temp = null;
        try (Response response = okHttpClient.newCall(request).execute()) {
            ResponseBody body = response.body();
            String bodyString = "";
            if (body != null) {
                bodyString = body.string();
            }
            logger.info("调用成功， bodyString is {}", bodyString);
            return new HttpResult(response.code(), response.protocol(), response.message(), body, bodyString);
        } catch (IOException e) {
            logger.error("okhttp3 error msg is {}", e.getMessage());
            throw new BusinessException(ErrorEnums.HTTP_REQUEST_ERROR, e.getMessage());
        }
    }

}

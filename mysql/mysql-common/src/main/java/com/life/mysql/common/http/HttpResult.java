package com.life.mysql.common.http;

import okhttp3.Protocol;
import okhttp3.ResponseBody;

import java.io.Serializable;

/**
 * @author tengyun
 * @date 2020/12/25 16:05
 **/
public class HttpResult implements Serializable {

    private static final long serialVersionUID = 1L;

    private int code;
    private Protocol protocol;
    private String message;
    private ResponseBody body;
    private String bodyString;

    public HttpResult() {
    }

    public HttpResult(int code, Protocol protocol, String message, ResponseBody body, String bodyString) {
        this.code = code;
        this.protocol = protocol;
        this.message = message;
        this.body = body;
        this.bodyString = bodyString;
    }

    public boolean isSuccessful() {
        return this.code >= 200 && this.code < 300;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Protocol getProtocol() {
        return protocol;
    }

    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResponseBody getBody() {
        return body;
    }

    public void setBody(ResponseBody body) {
        this.body = body;
    }

    public String getBodyString() {
        return bodyString;
    }

    public void setBodyString(String bodyString) {
        this.bodyString = bodyString;
    }

    @Override
    public String toString() {
        return "HttpResult{" +
            "code=" + code +
            ", protocol=" + protocol +
            ", message='" + message + '\'' +
            ", body=" + body +
            ", bodyString='" + bodyString + '\'' +
            '}';
    }
}

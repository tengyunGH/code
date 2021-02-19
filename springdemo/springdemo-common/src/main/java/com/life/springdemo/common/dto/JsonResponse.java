package com.life.springdemo.common.dto;

import com.life.springdemo.common.constants.ErrorEnums;

/**
 * @author tengyun
 * @date 2021/2/19 14:07
 **/
public class JsonResponse<T> {
    private int code;
    private String msg;
    private T data;

    public JsonResponse() {
    }

    public JsonResponse(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public JsonResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public JsonResponse(T data) {
        this.code = 0;
        this.msg = "成功";
        this.data = data;
    }

    public static JsonResponse<String> success() {
        return new JsonResponse<>(null);
    }

    public static JsonResponse<String> success(String data) {
        return new JsonResponse<>(data);
    }

    public static JsonResponse<Boolean> success(Boolean data) {
        return new JsonResponse<>(data);
    }

    public static JsonResponse<String> fail(int code, String msg) {
        return new JsonResponse<>(code, msg);
    }
    public static JsonResponse<String> fail(ErrorEnums errorEnums) {
        return new JsonResponse<>(errorEnums.getCode(), errorEnums.getMsg());
    }

    public static JsonResponse<String> fail(String msg) {
        return new JsonResponse<>(ErrorEnums.SERVER_ERROR.getCode(), msg);
    }

    public static JsonResponse<String> fail() {
        return new JsonResponse<>(ErrorEnums.SERVER_ERROR.getCode(), ErrorEnums.SERVER_ERROR.getMsg());
    }

    public boolean isSuccess() {
        return 0 == code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

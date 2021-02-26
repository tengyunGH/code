package com.life.common.constants;

/**
 * @author tengyun
 * @date 14:01 2021/2/19
 **/
public enum ErrorEnums {
    /**
     * 5000-5999 服务错误，未处理异常
     */
    SERVER_ERROR(5000, "服务错误！"),
    MQ_SEND_ERROR(5001, "MQ消息发送失败！"),
    ALIAPY_SEND_ERROR(5002, "支付宝小程序消息发送失败！"),
    HTTP_REQUEST_ERROR(5001, "http请求失败！"),
    /**
     * 6000-6999 用户相关错误
     */
    USER_AUTH_PHONE_WX_SERVER_ERROR(6000, "调用微信服务错误！"),
    USER_AUTH_PHONE_WX_ENCRYPTE_ERROR(6001, "微信数据解密失败！"),
    USER_NOT_EXISTS_ERROR(6002, "用户不存在！"),
    ID_CARD_NULL_ERROR(6003, "用户身份证号不能为空！"),
    PHONE_NULL_ERROR(6004, "手机号不能为空！"),
    PHONE_EXISTS_ERROR(6005, "手机号已经存在！"),
    ;

    private int code;
    private String msg;

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

    public String getMsg(Object... args) {
        return String.format(msg, args);
    }

    ErrorEnums(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}

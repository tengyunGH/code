package com.life.common.constants;

public enum RedisKeyEnums {
    /**
     * 微信token缓存
     * iot:user:app:token:wxapp:appId
     * 过期时间：1个小时
     */
    IOT_WX_TOKEN_KEY(1 * 60 * 60, "iot:user:app:token:wxapp:%s"),
    /**
     * 设备信息缓存
     * iot:platform:device
     * 过期时间：1天
     */
    IOT_DEVICE_EXISTS(24 * 60 * 60, "iot:platform:device:%s"),
    /**
     * 设备对应系统信息缓存
     * iot:platform:devicetosys
     * 过期时间：1天
     */
    IOT_DEVICE_SYS_EXISTS(24 * 60 * 60, "iot:platform:devicetosys:%s"),
    /**
     * 产品指标对应系统信息缓存
     * iot:platform:prdindicator
     * 过期时间：1天
     */
    IOT_INDICATOR_PRODUCT_EXISTS(24 * 60 * 60, "iot:platform:prdindicator:%s"),

    /**
     * 支付宝小程序用户formId
     * iot:platform:prdindicator
     * 过期时间：7天
     */
    USER_ALIPAY_FORMID(7 * 24 * 60 * 60, "user:alipay:userid:%s"),
    ;
    /**
     * 缓存时间，单位：秒
     */
    private int expireTime;
    /**
     * 缓存key
     */
    private String key;

    public int getExpireTime() {
        return expireTime;
    }

    /**
     * 格式化
     *
     * @param args
     * @return
     */
    public String getKey(Object... args) {
        return String.format(key, args);
    }

    RedisKeyEnums(int expireTime, String key) {
        this.expireTime = expireTime;
        this.key = key;
    }
}

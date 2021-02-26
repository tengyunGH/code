package com.life.seckill.common.constants;

/**
 * @author tengyun
 * @date 2021/2/23 10:52
 **/
public enum  RedisKeyEnums {
    /**
     * 商品信息
     * 过期时间：24小时
     **/
    GOODS_INFO(24 * 60 * 60, "seckill:goods:info:%s"),

    GOODS_STOCK(24 * 60 * 60, "seckill:goods:stock"),

    USER_ACCESS_NUM(10, "seckill:user:access:%s"),
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

    public void setExpireTime(int expireTime) {
        this.expireTime = expireTime;
    }

    public String getKey(Object... args) {
        return String.format(key, args);
    }

    public void setKey(String key) {
        this.key = key;
    }

    RedisKeyEnums(int expireTime, String key) {
        this.expireTime = expireTime;
        this.key = key;
    }
}

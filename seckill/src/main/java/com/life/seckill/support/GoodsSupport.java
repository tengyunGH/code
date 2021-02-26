package com.life.seckill.support;

import com.life.common.redis.RedisSupport;
import com.life.seckill.common.constants.RedisKeyEnums;
import com.life.seckill.dao.GoodsDao;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author tengyun
 * @date 2021/2/22 19:58
 **/
@Component
public class GoodsSupport implements InitializingBean {

    @Resource
    private RedisSupport redisSupport;

    @Resource
    private GoodsDao goodsDao;

    @Override
    public void afterPropertiesSet() {
        Map<String, String> map = goodsDao.queryById((long) 1);
        redisSupport.set(RedisKeyEnums.GOODS_STOCK.getKey(1), "100");
        redisSupport.hPutAll(RedisKeyEnums.GOODS_INFO.getKey(1), map);
    }

}

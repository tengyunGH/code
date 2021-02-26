package com.life.seckill.service;

import com.life.common.exception.BusinessException;
import com.life.common.redis.RedisSupport;
import com.life.seckill.common.constants.RedisKeyEnums;
import com.life.seckill.entity.Goods;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sun.security.provider.MD5;
import utils.encryption.MD5Util;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author tengyun
 * @date 2021/2/22 19:53
 **/
@Service
public class SeckillService {

    @Resource
    private RedisSupport redisSupport;

    @Value("${seckill.enable}")
    private String isSeckill;

    /**
     * @param goodsId 商品id
     * @return String
     * @author tengyun
     * @date 9:16 2021/2/23
     **/
    public String getUrl(Long goodsId) throws Exception {
        // 将商品id、是否开启秒杀、秒杀时间组成字符串并加密
        // redisSupport.hGetAll()
        Goods goods = new Goods();
        Long id = goods.getId();
        Date startTime = goods.getStartTime();
        Date endTime = goods.getEndTime();
        String s = id + String.valueOf(startTime) + endTime + isSeckill + "";
        return MD5Util.getMd5(s);
    }

    public void seckill(Long userId, Long goodsId, String url) throws Exception {
        // 1、校验url的正确性
        String url1 = getUrl(goodsId);
        if (!url1.equals(url)) {
            throw new BusinessException("秒杀url不合法");
        }
        // 2、限流，同一个用户，十秒内的重复请求不理会
        if (redisSupport.hasKey(RedisKeyEnums.USER_ACCESS_NUM.getKey(userId))) {
            throw new BusinessException("请勿重复请求");
        } else {
            // 不存在的话，存入redis
            redisSupport.setEx(RedisKeyEnums.USER_ACCESS_NUM.getKey(userId), "1", 10, TimeUnit.SECONDS);
        }
        // 3、令牌限流 TODO

        // 4、从redis中校验库存
        String s = redisSupport.get(RedisKeyEnums.GOODS_STOCK.getKey());
        if (Integer.parseInt(s) <= 0) {
            throw new BusinessException("秒杀活动已结束！");
        }

        // 5、数据库减库存，生成订单，redis减库存。此处如果想做成异步，那么将用户信息发mq，返回成功。秒杀结果采取回调或者发短信这种方式告诉用户。



    }
}

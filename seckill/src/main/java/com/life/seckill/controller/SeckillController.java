package com.life.seckill.controller;

import com.life.common.dto.JsonResponse;
import com.life.seckill.service.SeckillService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author tengyun
 * @date 2021/2/22 15:52
 **/

@RestController
public class SeckillController {

    @Resource
    private SeckillService seckillService;

    /**
     * 根据商品的信息生成一个随机串，秒杀时校验活动的合法性
     * @author tengyun
     * @date 19:57 2021/2/22
     * @param goodsId 秒杀的商品Id
     * @return com.life.common.dto.JsonResponse<java.lang.String>
     **/
    @GetMapping("/url/get")
    public JsonResponse<String> getUrl(@RequestParam Long goodsId) throws Exception {
        return new JsonResponse<>(seckillService.getUrl(goodsId));
    }

    @GetMapping("/sec/kill")
    public JsonResponse<String> secKill(@RequestParam Long userId, @RequestParam Long goodsId, @RequestParam String url) throws Exception {
        seckillService.seckill(userId, goodsId, url);
        return JsonResponse.success();
    }

}

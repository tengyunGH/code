package com.life.mysql.lock;

import com.life.mysql.service.GoodsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 10个线程同时修改一个字段stock，初始值100，想将这个减去num
 * 如果这么做： 查出stock值，更新stock值。事务开始时，加了共享锁，10个线程都能读，读到的都是10，然后加排它锁，此时只有一个线程可以改，但是最后的结果，我觉得是不对的
 *
 * @author tengyun
 * @date 2021/2/22 10:37
 **/
@RestController
public class TestUpdate {

    @Resource
    private GoodsService goodsService;

    @GetMapping("/test/pessimistic")
    public void test() throws Exception {
        goodsService.test();
    }

    @GetMapping("/test/optimistic")
    public void test2() {
        goodsService.optimistic();
    }

    @GetMapping("/test/one")
    public void test3() {
        goodsService.test3();
    }

}

package com.life.juc.threadpool;

/**
 * @author tengyun
 * @date 2021/2/5 14:50
 **/
public class BusinessServiceImpl implements BusinessService {
    @Override
    public Integer handlerService() {
        int res = 0;
        for (int i = 0; i < 100; i++) {
            res++;
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " is executing");
        return res;
    }
}

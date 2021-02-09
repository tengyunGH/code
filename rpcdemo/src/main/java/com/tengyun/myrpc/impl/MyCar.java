package com.tengyun.myrpc.impl;

import com.tengyun.myrpc.inter.Car;

/**
 * @author tengyun
 * @date 2021/2/3 9:43
 **/
public class MyCar implements Car {

    @Override
    public int run(int mileage) {
        return mileage;
    }

}

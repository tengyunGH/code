package com.life.springdemo.common.security.sign;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;

/**
 * @author tengyun
 * @date 2021/2/19 10:42
 **/
public class SignVerificationAspect {

    @Around(value = "@annotation(com.boe.mhealth.support.annotation.SignVerification)")   // 表示在@CheckSign注解的方法中使用这个方法
    public Object checkSign(ProceedingJoinPoint pjp) throws Throwable {
        // 获取目标方法的参数
        Object args[] = pjp.getArgs();


        return pjp.proceed(args);
    }
}

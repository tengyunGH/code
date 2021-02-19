package com.life.springdemo.common.security.sign;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author tengyun
 * @date 2021/2/19 10:44
 **/
@Target(ElementType.METHOD)  // 表示用于方法上的注解
@Retention(RetentionPolicy.RUNTIME)   // 会由虚拟机保留,以便它可以在运行时通过反射读取
@Documented   // 生成文档
public @interface SignVerification {
}

package com.tengyun.quartzdemo.exception;

import com.google.common.collect.Maps;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author tengyun
 * @date 2020/12/16 15:35
 **/
@ControllerAdvice
@ResponseBody
public class GlobalException {
    /**
     * 全局异常处理
     * @author tengyun
     * @date 19:13 2020/12/16
     * @param e 异常
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ExceptionHandler(value = Exception.class)//拦截所有异常
    public Map<String, Object> exceptionHandler(Exception e) {
        Map<String, Object> result = Maps.newHashMapWithExpectedSize(2);
        if (e instanceof BaseException) {
            BaseException ex = (BaseException) e;
            result.put("code", 200);
            result.put("msg", "cUOL额");
        } else {
            result.put("code", 5000);
            result.put("msg", "cuo");
        }
        return result;
    }
}

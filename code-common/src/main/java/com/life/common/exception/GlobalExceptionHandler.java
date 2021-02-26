package com.life.common.exception;

import com.life.common.constants.ErrorEnums;
import com.life.common.dto.JsonResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

/**
 * @author tengyun
 * @date 2021/2/19 14:04
 **/
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler
    public JsonResponse<String> exceptionHandler(Exception e) {
        e.printStackTrace();
        if (e instanceof BusinessException) {
            BusinessException ex = (BusinessException) e;
            return JsonResponse.fail(ex.getErrorCode(), ex.getErrorMsg());
        } else if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException ex = (MethodArgumentNotValidException) e;
            return JsonResponse.fail(Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage());
        } else {
            return JsonResponse.fail(ErrorEnums.SERVER_ERROR.getCode(), e.getMessage());
        }
    }
}

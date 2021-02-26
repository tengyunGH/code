package com.life.mysql.common.exception;


import com.life.mysql.common.constants.ErrorEnums;

/**
 * @author tengyun
 * @date 2021/2/19 14:09
 **/
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    /**
     * 错误码
     **/
    private int errorCode;
    /**
     * 错误信息
     **/
    private String errorMsg;

    public BusinessException() {

    }

    public BusinessException(int errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public BusinessException(ErrorEnums errorEnums, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorEnums.getCode();
        this.errorMsg = errorEnums.getMsg() + "->" + errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}

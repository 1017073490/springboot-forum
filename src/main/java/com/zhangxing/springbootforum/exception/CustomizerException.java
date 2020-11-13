package com.zhangxing.springbootforum.exception;

/**
 * @author zhangxing
 * @Description:
 * @date 2020/11/13 15:48
 */
public class CustomizerException extends RuntimeException {
    private String message;
    private Integer code;

    public CustomizerException(ICustomizerErrorCode errorCode) {
        this.message = errorCode.getMessage();
        this.code = errorCode.getCode();
    }

    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}

package com.railwayguide.common;

/**
 * 业务异常，携带业务状态码。
 * 由 GlobalExceptionHandler 统一捕获并转换为 Result 响应。
 */
public class BusinessException extends RuntimeException {

    private final Integer code;

    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}

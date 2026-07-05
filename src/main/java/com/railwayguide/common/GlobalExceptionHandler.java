package com.railwayguide.common;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器：将异常统一转换为 {@link Result} 响应（HTTP 200，业务码区分成败）。
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /** 业务异常：按业务码返回 */
    @ExceptionHandler(BusinessException.class)
    public Result<Void> handleBusinessException(BusinessException e) {
        return Result.error(e.getCode(), e.getMessage());
    }

    /** 兜底异常：避免向前端泄露内部堆栈 */
    @ExceptionHandler(Exception.class)
    public Result<Void> handleException(Exception e) {
        return Result.error(500, "服务器内部错误，请联系管理员");
    }
}

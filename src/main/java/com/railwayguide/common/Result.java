package com.railwayguide.common;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 统一响应结果包装类
 *
 * @param <T> 业务数据类型
 */
@Schema(description = "统一响应结果")
public class Result<T> {

    @Schema(description = "业务状态码：200 成功，其它为失败", example = "200")
    private Integer code;

    @Schema(description = "提示信息", example = "登录成功")
    private String message;

    @Schema(description = "响应数据")
    private T data;

    public Result() {
    }

    public Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(200, "操作成功", data);
    }

    public static <T> Result<T> success(String message, T data) {
        return new Result<>(200, message, data);
    }

    public static <T> Result<T> error(Integer code, String message) {
        return new Result<>(code, message, null);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

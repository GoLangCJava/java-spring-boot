package com.railwayguide.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

/**
 * 登录成功后返回给前端的数据（不含密码等敏感字段）
 */
@Schema(description = "登录成功返回数据")
public class LoginResponseVO {

    @Schema(description = "用户 ID", example = "1")
    private Integer userId;

    @Schema(description = "用户名", example = "admin")
    private String username;

    @Schema(description = "登录时间", example = "2026-07-05T17:37:56")
    private LocalDateTime loginTime;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(LocalDateTime loginTime) {
        this.loginTime = loginTime;
    }
}

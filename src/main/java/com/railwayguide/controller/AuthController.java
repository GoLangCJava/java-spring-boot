package com.railwayguide.controller;

import com.railwayguide.common.Result;
import com.railwayguide.dto.LoginRequest;
import com.railwayguide.dto.LoginResponseVO;
import com.railwayguide.entity.User;
import com.railwayguide.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Tag(name = "Auth", description = "认证相关 API")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Operation(summary = "用户登录", description = "通过用户名和密码登录，校验通过后返回用户基本信息（不含密码）")
    @PostMapping("/login")
    public Result<LoginResponseVO> login(@RequestBody LoginRequest request) {
        User user = authService.login(request);

        LoginResponseVO vo = new LoginResponseVO();
        vo.setUserId(user.getId());
        vo.setUsername(user.getUsername());
        vo.setLoginTime(LocalDateTime.now());

        return Result.success("登录成功", vo);
    }
}

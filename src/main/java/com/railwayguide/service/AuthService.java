package com.railwayguide.service;

import com.railwayguide.common.BusinessException;
import com.railwayguide.dto.LoginRequest;
import com.railwayguide.entity.User;
import com.railwayguide.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    /**
     * 用户登录：根据用户名查库并校验密码。
     * 注意：当前为明文比对，生产环境建议改用 BCrypt 等加密方式存储与校验密码。
     *
     * @param request 登录请求（用户名 + 密码）
     * @return 校验通过的用户实体
     * @throws BusinessException 用户名或密码为空（400）、账号或密码错误（401）
     */
    public User login(LoginRequest request) {
        if (request == null
                || request.getUsername() == null || request.getUsername().isBlank()
                || request.getPassword() == null || request.getPassword().isBlank()) {
            throw new BusinessException(400, "用户名和密码不能为空");
        }

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new BusinessException(401, "用户名或密码错误"));

        // 明文密码比对
        if (!request.getPassword().equals(user.getPassword())) {
            throw new BusinessException(401, "用户名或密码错误");
        }
        return user;
    }
}

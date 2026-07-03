package com.railwayguide.controller;

import com.railwayguide.entity.User;
import com.railwayguide.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Tag(name = "Users", description = "用户管理 API")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "获取所有用户", description = "返回所有用户列表，不包含密码信息")
    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @Operation(summary = "根据 ID 获取用户", description = "通过用户 ID 查询单个用户信息")
    @Parameter(name = "id", description = "用户 ID", required = true)
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "根据用户名查找用户", description = "通过用户名查询用户信息")
    @Parameter(name = "username", description = "用户名", required = true)
    @GetMapping("/search")
    public ResponseEntity<User> getUserByUsername(@RequestParam String username) {
        return userService.findByUsername(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "创建新用户", description = "提交用户名和密码创建用户")
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User created = userService.create(user);
        return ResponseEntity.created(URI.create("/api/users/" + created.getId())).body(created);
    }

    @Operation(summary = "更新用户信息", description = "根据用户 ID 更新用户名或密码")
    @Parameter(name = "id", description = "用户 ID", required = true)
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User user) {
        try {
            User updated = userService.update(id, user);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "删除用户", description = "根据用户 ID 删除用户")
    @Parameter(name = "id", description = "用户 ID", required = true)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService.findById(id)
                .map(u -> {
                    userService.delete(id);
                    return ResponseEntity.<Void>noContent().build();
                });
        return ResponseEntity.notFound().build();
    }
}

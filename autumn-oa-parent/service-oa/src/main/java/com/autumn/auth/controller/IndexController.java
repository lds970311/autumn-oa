package com.autumn.auth.controller;

import com.autumn.result.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Tag(name = "后台登录管理")
@RequestMapping("/admin/system/index")
public class IndexController {
    /**
     * 登录
     *
     * @return
     */
    @PostMapping("login")
    @Operation(summary = "后台登录")
    public Result<Map<String, Object>> login() {
        Map<String, Object> map = new HashMap<>();
        map.put("token", "admin");
        return Result.ok(map);
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    @GetMapping("info")
    @Operation(summary = "获取当前用户信息")
    public Result<Map<String, Object>> info() {
        Map<String, Object> map = new HashMap<>();
        map.put("roles", "[admin]");
        map.put("name", "admin");
        map.put("avatar", "https://oss.aliyuncs.com/aliyun_id_photo_bucket/default_handsome.jpg");
        return Result.ok(map);
    }

    /**
     * 退出
     *
     * @return
     */
    @Operation(summary = "退出登录")
    @PostMapping("logout")
    public Result<Void> logout() {
        return Result.ok();
    }

}

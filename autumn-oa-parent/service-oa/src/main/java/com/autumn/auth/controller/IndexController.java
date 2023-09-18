package com.autumn.auth.controller;

import com.autumn.auth.model.system.SysUser;
import com.autumn.auth.service.SysMenuService;
import com.autumn.auth.service.SysUserService;
import com.autumn.auth.vo.system.LoginVo;
import com.autumn.auth.vo.system.RouterVo;
import com.autumn.common.JwtHelper;
import com.autumn.result.Result;
import com.autumn.util.MD5;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Tag(name = "后台登录管理")
@RequestMapping("/admin/system/index")
public class IndexController {
    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 登录
     *
     * @return
     */
    @PostMapping("login")
    @Operation(summary = "后台登录")
    public Result<Object> login(@RequestBody LoginVo loginVo,HttpServletRequest request) {
        String username = loginVo.getUsername();
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getUsername, username);
        SysUser sysUser = sysUserService.getOne(wrapper);
        if (sysUser == null) {
            return Result.fail("用户名不存在");
        }
        String userPwd = MD5.encrypt(loginVo.getPassword());
        if (!userPwd.equals(sysUser.getPassword())) {
            return Result.fail("用户密码错误!");
        }
        if (sysUser.getStatus() == 0) {
            //用户被禁用
            return Result.fail("该账号已被锁定");
        }
        //生成token
        String token = JwtHelper.createToken(sysUser.getId(), sysUser.getUsername());
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);

        String requestURI = request.getRequestURI();
        return Result.ok(map);
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    @GetMapping("info")
    @Operation(summary = "获取当前用户信息")
    public Result<Map<String, Object>> info(HttpServletRequest request) {
        //1 从请求头获取用户信息（获取请求头token字符串）
        String token = request.getHeader("token");

        //2 从token字符串获取用户id 或者 用户名称
        Long userId = JwtHelper.getUserId(token);

        //3 根据用户id查询数据库，把用户信息获取出来
        SysUser sysUser = sysUserService.getById(userId);

        //4 根据用户id获取用户可以操作菜单列表
        //查询数据库动态构建路由结构，进行显示
        List<RouterVo> routerList = sysMenuService.findUserMenuListByUserId(userId);

        //5 根据用户id获取用户可以操作按钮列表
        List<String> permsList = sysMenuService.findUserPermsByUserId(userId);

        //6 返回相应的数据
        Map<String, Object> map = new HashMap<>();
        map.put("roles", "[admin]");
        map.put("name", sysUser.getName());
        map.put("avatar", sysUser.getHeadUrl());
        map.put("routers", routerList);
        map.put("buttons", permsList);
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

package com.autumn.auth.controller;

import com.autumn.auth.model.system.SysUser;
import com.autumn.auth.service.SysUserService;
import com.autumn.auth.vo.system.SysUserQueryVo;
import com.autumn.result.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin/system/sysUser")
@Tag(name = "系统用户接口")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    @Operation(description = "用户条件分页查询")
    @GetMapping("{page}/{limit}")
    public Result<Map<String, Object>> getUserByPage(@PathVariable("page") Integer pageNo,
                                                     @PathVariable("limit") Integer limit,
                                                     SysUserQueryVo sysUserQueryVo) {


        Map<String, Object> resultMap = sysUserService.getUserByPage(pageNo, limit, sysUserQueryVo);
        return Result.ok(resultMap);
    }

    @Operation(description = "获取用户")
    @GetMapping("get/{id}")
    public Result<SysUser> get(@PathVariable Long id) {
        SysUser user = sysUserService.getById(id);
        return Result.ok(user);
    }

    @Operation(description = "新增用户")
    @PostMapping("save")
    public Result<Void> save(@RequestBody SysUser user) {
        boolean b = sysUserService.save(user);
        return b ? Result.ok() : Result.fail();
    }

    @Operation(description = "更新用户")
    @PutMapping("update")
    public Result<Void> updateById(@RequestBody SysUser user) {
        boolean b = sysUserService.updateById(user);
        return b ? Result.ok() : Result.fail();
    }

    @Operation(description = "删除用户")
    @DeleteMapping("remove/{id}")
    public Result<Void> remove(@PathVariable Long id) {
        boolean b = sysUserService.removeById(id);
        return b ? Result.ok() : Result.fail();
    }

    @Operation(summary = "更新状态")
    @GetMapping("updateStatus/{id}/{status}")
    public Result<Void> updateStatus(@PathVariable Long id, @PathVariable Integer status) {
        boolean b = sysUserService.updateStatus(id, status);
        return b ? Result.ok() : Result.fail();
    }
}

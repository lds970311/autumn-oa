package com.autumn.auth.controller;

import com.alibaba.druid.util.StringUtils;
import com.autumn.auth.model.system.SysRole;
import com.autumn.auth.service.SysRoleService;
import com.autumn.auth.vo.system.AssginRoleVo;
import com.autumn.auth.vo.system.SysRoleQueryVo;
import com.autumn.result.Result;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/system/sysRole")
@Tag(name = "角色管理")
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;


    @GetMapping("/findAll")
    @Operation(summary = "查询所有角色")
    public Result<List<SysRole>> findAll() {
        List<SysRole> list = sysRoleService.list();
        return Result.ok(list);
    }

    @Operation(summary = "条件分页查询")
    @GetMapping("/{page}/{limit}")
    public Result<Map<String, Object>> findRoleByPage(@PathVariable("page") Integer page,
                                                      @PathVariable("limit") Integer limit,
                                                      SysRoleQueryVo sysRoleQueryVo) {
        Page<SysRole> sysRolePage = new Page<>(page, limit);
        LambdaQueryWrapper<SysRole> wrapper = new LambdaQueryWrapper<>();
        String roleName = sysRoleQueryVo.getRoleName();
        if (!StringUtils.isEmpty(roleName)) {
            wrapper.like(SysRole::getRoleName, roleName);
        }
        sysRoleService.page(sysRolePage, wrapper);
        Map<String, Object> map = new HashMap<>();
        map.put("records", sysRolePage.getRecords());
        map.put("total", sysRolePage.getTotal());
        map.put("current", sysRolePage.getCurrent());
        return Result.ok(map);
    }

    @PreAuthorize("hasAuthority('bnt.sysRole.add')")
    @Operation(summary = "添加角色")
    @PostMapping("/save")
    public Result<String> addRole(@RequestBody SysRole sysRole) {
        boolean save = sysRoleService.save(sysRole);
        if (save) {
            return Result.ok("添加成功");
        }
        return Result.fail("添加失败");
    }


    @PreAuthorize("hasAuthority('bnt.sysRole.list')")
    @Operation(summary = "根据id查询角色")
    @GetMapping("/get/{id}")
    public Result<SysRole> getRoleById(@PathVariable("id") Integer id) {
        SysRole role = sysRoleService.getById(id);
        return Result.ok(role);
    }


    @PreAuthorize("hasAuthority('bnt.sysRole.update')")
    @Operation(summary = "更新角色")
    @PutMapping("update")
    public Result<String> updateRole(@RequestBody SysRole sysRole) {
        boolean b = sysRoleService.updateById(sysRole);
        if (b) {
            return Result.ok("更新角色成功!");
        }
        return Result.fail("更新失败! 请重试");
    }

    @PreAuthorize("hasAuthority('bnt.sysRole.remove')")
    @Operation(summary = "根据id删除")
    @DeleteMapping("/remove/{id}")
    public Result<String> removeById(@PathVariable("id") Integer id) {
        boolean b = sysRoleService.removeById(id);
        if (b) {
            return Result.ok("删除角色成功!");
        }
        return Result.fail("删除失败! 请重试");
    }

    @PreAuthorize("hasAuthority('bnt.sysRole.remove')")
    @Operation(summary = "批量删除")
    @DeleteMapping("batchDelete")
    public Result<String> batchDelete(@RequestBody List<Integer> idList) {
        boolean b = sysRoleService.removeByIds(idList);
        if (b) {
            return Result.ok("批量删除角色成功!");
        }
        return Result.fail("批量删除角色失败! 请重试");
    }


    @Operation(summary = "根据用户查询角色")
    @GetMapping("toAssign/{userId}")
    public Result<Map<String, Object>> toAssign(@PathVariable("userId") Long userId) {
        Map<String, Object> res = sysRoleService.findRoleInfoByUserId(userId);
        return Result.ok(res);
    }

    @Operation(summary = "根据用户分配角色")
    @PostMapping("/doAssign")
    public Result<Void> doAssign(@RequestBody AssginRoleVo assginRoleVo) {
        sysRoleService.doAssign(assginRoleVo);
        return Result.ok();
    }

}


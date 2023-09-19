package com.autumn.auth.controller;

import com.autumn.auth.model.system.SysMenu;
import com.autumn.auth.service.SysMenuService;
import com.autumn.auth.vo.system.AssginMenuVo;
import com.autumn.result.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/system/sysMenu")
@Tag(name = "菜单管理")
public class SysMenuController {
    @Autowired
    private SysMenuService sysMenuService;


    @Operation(summary = "获取菜单")
    @GetMapping("/findNodes")
    public Result<List<SysMenu>> findNodes() {
        List<SysMenu> list = sysMenuService.findNodes();
        return Result.ok(list);
    }

    @PreAuthorize("hasAuthority('bnt.sysMenu.add')")
    @Operation(summary = "新增菜单")
    @PostMapping("/save")
    public Result<Void> save(@RequestBody SysMenu permission) {
        boolean b = sysMenuService.save(permission);
        return b ? Result.ok() : Result.fail();
    }

    @PreAuthorize("hasAuthority('bnt.sysMenu.update')")
    @Operation(summary = "修改菜单")
    @PutMapping("/update")
    public Result<Void> updateById(@RequestBody SysMenu permission) {
        boolean b = sysMenuService.updateById(permission);
        return b ? Result.ok() : Result.fail();
    }

    @PreAuthorize("hasAuthority('bnt.sysMenu.remove')")
    @Operation(summary = "删除菜单")
    @DeleteMapping("remove/{id}")
    public Result<String> remove(@PathVariable Long id) {
        boolean b = sysMenuService.removeMenuById(id);
        return b ? Result.ok("删除成功") : Result.fail("该菜单下存在子菜单,不能删除");
    }

    @Operation(summary = "根据角色获取菜单")
    @GetMapping("toAssign/{roleId}")
    public Result toAssign(@PathVariable Long roleId) {
        List<SysMenu> list = sysMenuService.findSysMenuByRoleId(roleId);
        return Result.ok(list);
    }

    @PreAuthorize("hasAuthority('bnt.sysUser.assignRole')")
    @Operation(summary = "给角色分配权限")
    @PostMapping("/doAssign")
    public Result doAssign(@RequestBody AssginMenuVo assignMenuVo) {
        boolean b = sysMenuService.doAssign(assignMenuVo);
        return b ? Result.ok() : Result.fail();
    }
}

package com.autumn.auth.controller;

import com.autumn.auth.model.system.SysOperLog;
import com.autumn.auth.service.SysOperLogService;
import com.autumn.result.Result;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/system/sysOperLog")
@Tag(name = "操作日志管理")
public class SysOperLogController {
    @Autowired
    private SysOperLogService sysOperLogService;

    @GetMapping("/allLogs/{page}/{limit}")
    @Operation(summary = "获取日志")
    public Result<Map<String, Object>> getAllLogs(@PathVariable("page") Integer page,
                                                  @PathVariable("limit") Integer limit) {
        Page<SysOperLog> logPage = new Page<>(page, limit);
        sysOperLogService.page(logPage);

        List<SysOperLog> records = logPage.getRecords();
        long total = logPage.getTotal();
        long current = logPage.getCurrent();

        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("current", current);
        map.put("records", records);
        return Result.ok(map);
    }
}

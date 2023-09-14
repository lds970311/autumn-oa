package com.autumn.auth.service.impl;


import com.alibaba.druid.util.StringUtils;
import com.autumn.auth.model.system.SysUser;
import com.autumn.auth.service.SysUserService;
import com.autumn.auth.mapper.SysUserMapper;
import com.autumn.auth.vo.system.SysUserQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 26966
 * @description 针对表【sys_user(用户表)】的数据库操作Service实现
 * @createDate 2023-09-14 09:50:14
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Override
    public Map<String, Object> getUserByPage(Integer pageNo, Integer limit, SysUserQueryVo sysUserQueryVo) {
        Page<SysUser> sysUserPage = new Page<>(pageNo, limit);
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        String keyword = sysUserQueryVo.getKeyword();
        String timeBegin = sysUserQueryVo.getCreateTimeBegin();
        String timeEnd = sysUserQueryVo.getCreateTimeEnd();
        if (!StringUtils.isEmpty(keyword)) {
            wrapper.like(SysUser::getName, keyword);
        }

        if (!StringUtils.isEmpty(timeBegin)) {
            wrapper.ge(SysUser::getCreateTime, timeBegin);
        }
        if (!StringUtils.isEmpty(timeEnd)) {
            wrapper.le(SysUser::getCreateTime, timeEnd);
        }
        this.baseMapper.selectPage(sysUserPage, wrapper);
        List<SysUser> records = sysUserPage.getRecords();
        records.forEach(record -> record.setPassword(null));
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("records", records);
        resultMap.put("total", sysUserPage.getTotal());
        resultMap.put("current", sysUserPage.getCurrent());
        return resultMap;
    }

    @Override
    public boolean updateStatus(Long id, Integer status) {
        SysUser sysUser = this.baseMapper.selectById(id);
        sysUser.setStatus(status);
        return saveOrUpdate(sysUser);
    }
}





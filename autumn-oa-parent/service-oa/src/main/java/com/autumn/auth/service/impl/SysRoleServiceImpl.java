package com.autumn.auth.service.impl;


import com.autumn.auth.mapper.SysRoleMapper;
import com.autumn.auth.mapper.SysUserRoleMapper;
import com.autumn.auth.model.system.SysRole;
import com.autumn.auth.model.system.SysUserRole;
import com.autumn.auth.service.SysRoleService;
import com.autumn.auth.service.SysUserRoleService;
import com.autumn.auth.vo.system.AssginRoleVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 26966
 * @description 针对表【sys_role(角色)】的数据库操作Service实现
 * @createDate 2023-09-12 12:35:59
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    private SysUserRoleService sysUserRoleService;


    @Override
    public Map<String, Object> findRoleInfoByUserId(Long userId) {
        //查询所有的角色
        List<SysRole> allRolesList = this.list();

        LambdaQueryWrapper<SysUserRole> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(SysUserRole::getUserId, userId)
                .select(SysUserRole::getRoleId);

        //拥有的角色id
        List<SysUserRole> existUserRoleList = sysUserRoleMapper.selectList(wrapper);
        List<Long> existRoleIdList = existUserRoleList.stream().map(SysUserRole::getRoleId).toList();

        //对角色进行分类
        List<SysRole> assginRoleList = new ArrayList<>();
        for (SysRole role : allRolesList) {
            //已分配
            if (existRoleIdList.contains(role.getId())) {
                assginRoleList.add(role);
            }
        }

        Map<String, Object> roleMap = new HashMap<>();
        roleMap.put("assignRoleList", assginRoleList);
        roleMap.put("allRolesList", allRolesList);
        return roleMap;
    }

    @Override
    @Transactional
    public void doAssign(AssginRoleVo assginRoleVo) {
        LambdaQueryWrapper<SysUserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUserRole::getUserId, assginRoleVo.getUserId());
        sysUserRoleService.remove(wrapper); //删除原有角色

        //获取信的role_ids
        List<Long> roleIdList = assginRoleVo.getRoleIdList();
        for (Long l : roleIdList) {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setRoleId(l);
            sysUserRole.setUserId(assginRoleVo.getUserId());
            sysUserRoleService.save(sysUserRole);
        }
    }
}





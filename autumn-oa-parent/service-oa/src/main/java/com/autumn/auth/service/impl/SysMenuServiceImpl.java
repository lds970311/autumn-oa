package com.autumn.auth.service.impl;


import com.autumn.auth.mapper.SysRoleMenuMapper;
import com.autumn.auth.model.system.SysMenu;
import com.autumn.auth.model.system.SysRoleMenu;
import com.autumn.auth.service.SysMenuService;
import com.autumn.auth.mapper.SysMenuMapper;
import com.autumn.auth.vo.system.AssginMenuVo;
import com.autumn.util.MenuHelper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 26966
 * @description 针对表【sys_menu(菜单表)】的数据库操作Service实现
 * @createDate 2023-09-14 22:17:27
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {
    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public List<SysMenu> findNodes() {
        List<SysMenu> sysMenus = this.baseMapper.selectList(null);
        return MenuHelper.buildTree(sysMenus);
    }

    @Override
    public boolean removeMenuById(Long id) {
        LambdaQueryWrapper<SysMenu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysMenu::getParentId, id);
        Long count = this.baseMapper.selectCount(wrapper);
        if (count > 0) {
            //有子菜单
            return false;
        }
        this.baseMapper.deleteById(id);
        return true;
    }

    @Override
    public List<SysMenu> findSysMenuByRoleId(Long roleId) {
        //查询所有status=1的菜单
        LambdaQueryWrapper<SysMenu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysMenu::getStatus, 1);
        List<SysMenu> allMenus = this.list(wrapper);

        //根据roleId查询对应menu_id
        LambdaQueryWrapper<SysRoleMenu> menuLambdaQueryWrapper = new LambdaQueryWrapper<>();
        menuLambdaQueryWrapper.eq(SysRoleMenu::getRoleId, roleId)
                .select(SysRoleMenu::getMenuId);
        List<SysRoleMenu> sysRoleMenus = sysRoleMenuMapper.selectList(menuLambdaQueryWrapper);
        List<Long> menu_ids = sysRoleMenus.stream()
                .map(SysRoleMenu::getMenuId)
                .toList();

        for (SysMenu allMenu : allMenus) {
            if (menu_ids.contains(allMenu.getId())) {
                allMenu.setSelect(true);
            } else {
                allMenu.setSelect(false);
            }

        }
        return MenuHelper.buildTree(allMenus);
    }

    @Override
    @Transactional
    public boolean doAssign(AssginMenuVo assignMenuVo) {
        LambdaQueryWrapper<SysRoleMenu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysRoleMenu::getRoleId, assignMenuVo.getRoleId());
        sysRoleMenuMapper.delete(wrapper);
        List<Long> menuIdList = assignMenuVo.getMenuIdList();
        for (Long id : menuIdList) {
            SysRoleMenu sysRoleMenu = new SysRoleMenu();
            sysRoleMenu.setMenuId(id);
            sysRoleMenu.setRoleId(assignMenuVo.getRoleId());
            sysRoleMenuMapper.insert(sysRoleMenu);
        }

        return true;
    }
}





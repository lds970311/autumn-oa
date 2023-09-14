package com.autumn.auth.service.impl;


import com.autumn.auth.model.system.SysMenu;
import com.autumn.auth.service.SysMenuService;
import com.autumn.auth.mapper.SysMenuMapper;
import com.autumn.util.MenuHelper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 26966
 * @description 针对表【sys_menu(菜单表)】的数据库操作Service实现
 * @createDate 2023-09-14 22:17:27
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

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
}





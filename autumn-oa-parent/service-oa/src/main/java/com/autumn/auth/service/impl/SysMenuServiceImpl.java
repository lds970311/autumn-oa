package com.autumn.auth.service.impl;


import com.alibaba.druid.util.StringUtils;
import com.autumn.auth.mapper.SysMenuMapper;
import com.autumn.auth.mapper.SysRoleMenuMapper;
import com.autumn.auth.model.system.SysMenu;
import com.autumn.auth.model.system.SysRoleMenu;
import com.autumn.auth.service.SysMenuService;
import com.autumn.auth.vo.system.AssginMenuVo;
import com.autumn.auth.vo.system.MetaVo;
import com.autumn.auth.vo.system.RouterVo;
import com.autumn.util.MenuHelper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

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
                .collect(Collectors.toList());

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

    /**
     * 查询数据库动态构建路由结构
     *
     * @param userId
     * @return
     */
    @Override
    public List<RouterVo> findUserMenuListByUserId(Long userId) {
        List<SysMenu> menuList;
        if (userId == 1) {
            //是admin,查询全部菜单
            LambdaQueryWrapper<SysMenu> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(SysMenu::getStatus, 1)
                    .orderByAsc(SysMenu::getSortValue);
            menuList = this.baseMapper.selectList(wrapper);
        } else {
            menuList = this.baseMapper.findMenuListByUserId(userId);
        }
        List<SysMenu> tree = MenuHelper.buildTree(menuList);
        return this.buildRouter(tree);
    }

    private List<RouterVo> buildRouter(List<SysMenu> tree) {
        List<RouterVo> routers = new ArrayList<>();

        for (SysMenu menu : tree) {
            RouterVo routerVo = new RouterVo();
            routerVo.setHidden(false);
            routerVo.setAlwaysShow(false);
            routerVo.setPath(getRouterPath(menu));
            routerVo.setComponent(menu.getComponent());
            routerVo.setMeta(new MetaVo(menu.getName(), menu.getIcon()));
            //下一层
            List<SysMenu> children = menu.getChildren();
            if (menu.getType() == 1) {
                //加载出来下面隐藏路由
                List<SysMenu> hiddenMenuList = children.stream()
                        .filter(item -> !StringUtils.isEmpty(item.getComponent()))
                        .collect(Collectors.toList());
                for (SysMenu hidden : hiddenMenuList) {
                    RouterVo hiddenRouter = new RouterVo();
                    hiddenRouter.setHidden(true);
                    hiddenRouter.setAlwaysShow(false);
                    hiddenRouter.setPath(getRouterPath(hidden));
                    hiddenRouter.setComponent(hidden.getComponent());
                    hiddenRouter.setMeta(new MetaVo(hidden.getName(), hidden.getIcon()));
                    routers.add(hiddenRouter);
                }
            } else {
                if (!CollectionUtils.isEmpty(children)) {
                    if (children.size() > 0) {
                        routerVo.setAlwaysShow(true);
                    }
                    routerVo.setChildren(buildRouter(children));
                }

            }
            routers.add(routerVo);
        }
        return routers;
    }

    /**
     * 获取路由地址
     *
     * @param menu 菜单信息
     * @return 路由地址
     */
    private String getRouterPath(SysMenu menu) {
        String routerPath = "/" + menu.getPath();
        if (menu.getParentId().intValue() != 0) {
            routerPath = menu.getPath();
        }
        return routerPath;
    }

    @Override
    public List<String> findUserPermsByUserId(Long userId) {
        List<SysMenu> menuList;
        //判断是否是admin
        if (userId == 1) {
            LambdaQueryWrapper<SysMenu> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(SysMenu::getStatus, 1);
            menuList = this.baseMapper.selectList(wrapper);
        } else {
            menuList = this.baseMapper.findMenuListByUserId(userId);
        }
        List<String> permList = menuList.stream()
                .map(SysMenu::getPerms)
                .filter(perms -> !StringUtils.isEmpty(perms))
                .collect(Collectors.toList());
        return permList;
    }
}





package com.autumn.util;

import com.autumn.auth.model.system.SysMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MenuHelper {
    public static List<SysMenu> buildTree(List<SysMenu> sysMenus) {
        List<SysMenu> tree = new ArrayList<>();
        for (SysMenu sysMenu : sysMenus) {
            if (sysMenu.getParentId() == 0) {
                tree.add(getChildren(sysMenu, sysMenus));
            }
        }
        return tree;
    }

    private static SysMenu getChildren(SysMenu sysMenu, List<SysMenu> sysMenus) {
        sysMenu.setChildren(new ArrayList<>());
        for (SysMenu menu : sysMenus) {
            if (Objects.equals(sysMenu.getId(), menu.getParentId())) {
                if (sysMenu.getChildren() == null) {
                    sysMenu.setChildren(new ArrayList<>());
                }
                sysMenu.getChildren().add(getChildren(menu, sysMenus));
            }
        }
        return sysMenu;
    }
}

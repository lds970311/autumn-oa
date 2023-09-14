package com.autumn.auth.service;


import com.autumn.auth.model.system.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 26966
* @description 针对表【sys_menu(菜单表)】的数据库操作Service
* @createDate 2023-09-14 22:17:27
*/
public interface SysMenuService extends IService<SysMenu> {

    List<SysMenu> findNodes();

    boolean removeMenuById(Long id);
}

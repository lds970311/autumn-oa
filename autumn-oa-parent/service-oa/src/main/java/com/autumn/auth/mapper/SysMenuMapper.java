package com.autumn.auth.mapper;


import com.autumn.auth.model.system.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 26966
 * @description 针对表【sys_menu(菜单表)】的数据库操作Mapper
 * @createDate 2023-09-14 22:17:27
 * @Entity com.autumn.auth.pojo.SysMenu
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<SysMenu> findMenuListByUserId(@Param("userId") Long userId);
}





package com.autumn.auth.service;


import com.autumn.auth.model.system.SysRole;
import com.autumn.auth.vo.system.AssginRoleVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author 26966
* @description 针对表【sys_role(角色)】的数据库操作Service
* @createDate 2023-09-12 12:35:59
*/
public interface SysRoleService extends IService<SysRole> {

    Map<String, Object> findRoleInfoByUserId(Long userId);

    void doAssign(AssginRoleVo assginRoleVo);
}

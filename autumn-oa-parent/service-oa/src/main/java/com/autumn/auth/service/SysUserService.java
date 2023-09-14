package com.autumn.auth.service;


import com.autumn.auth.model.system.SysUser;
import com.autumn.auth.vo.system.SysUserQueryVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @author 26966
 * @description 针对表【sys_user(用户表)】的数据库操作Service
 * @createDate 2023-09-14 09:50:14
 */
public interface SysUserService extends IService<SysUser> {

    Map<String, Object> getUserByPage(Integer pageNo, Integer limit, SysUserQueryVo sysUserQueryVo);

    boolean updateStatus(Long id, Integer status);
}

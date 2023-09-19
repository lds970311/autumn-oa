
package com.autumn.auth.service.impl;

import com.autumn.auth.model.system.SysUser;
import com.autumn.auth.service.SysMenuService;
import com.autumn.auth.service.SysUserService;
import com.autumn.custom.CustomUser;
import com.autumn.custom.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysMenuService sysMenuService;


    /**
     * 根据用户名获取用户对象（获取不到直接抛异常）
     *
     * @param username
     */

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserService.getByUsername(username);
        if (null == sysUser) {
            throw new UsernameNotFoundException("用户名不存在！");
        }

        if (sysUser.getStatus() == 0) {
            throw new RuntimeException("账号已停用");
        }
        //根据userid查询权限数据,并返回
        List<GrantedAuthority> authorities = new ArrayList<>();
        List<String> userPermsByUserId = sysMenuService.findUserPermsByUserId(sysUser.getId());
        for (String perm : userPermsByUserId) {
            authorities.add(new SimpleGrantedAuthority(perm));
        }

        return new CustomUser(sysUser, authorities);
    }
}


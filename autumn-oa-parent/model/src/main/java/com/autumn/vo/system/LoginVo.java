package com.autumn.vo.system;


import lombok.Data;

/**
 * 登录对象
 */
@Data
public class LoginVo {

    /**
     * 手机号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

}

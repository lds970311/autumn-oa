package com.autumn.auth.model.system;


import com.autumn.auth.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "SysLoginLog")
@TableName("sys_login_log")
public class SysLoginLog extends BaseEntity {


	private static final long serialVersionUID = 1L;

	@Schema(title = "用户账号")
	@TableField("username")
	private String username;

	@Schema(title = "登录IP地址")
	@TableField("ipaddr")
	private String ipaddr;

	@Schema(title = "登录状态（0成功 1失败）")
	@TableField("status")
	private Integer status;

	@Schema(title = "提示信息")
	@TableField("msg")
	private String msg;

	@Schema(title = "访问时间")
	@TableField("access_time")
	private Date accessTime;

}
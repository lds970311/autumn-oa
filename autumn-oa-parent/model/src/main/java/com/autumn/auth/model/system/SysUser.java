package com.autumn.auth.model.system;


import com.autumn.auth.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "用户")
@TableName("sys_user")
public class SysUser extends BaseEntity {
	

	private static final long serialVersionUID = 1L;

	@Schema(title = "用户名")
	@TableField("username")
	private String username;

	@Schema(title = "密码")
	@TableField("password")
	private String password;

	@Schema(title = "姓名")
	@TableField("name")
	private String name;

	@Schema(title = "手机")
	@TableField("phone")
	private String phone;

	@Schema(title = "头像地址")
	@TableField("head_url")
	private String headUrl;

	@Schema(title = "部门id")
	@TableField("dept_id")
	private Long deptId;

	@Schema(title = "岗位id")
	@TableField("post_id")
	private Long postId;

	@Schema(title = "描述")
	@TableField("description")
	private String description;

	@Schema(title = "openId")
	@TableField("open_id")
	private String openId;

	@Schema(title = "状态（1：正常 0：停用）")
	@TableField("status")
	private Integer status;

	@TableField(exist = false)
	private List<SysRole> roleList;
	//岗位
	@TableField(exist = false)
	private String postName;
	//部门
	@TableField(exist = false)
	private String deptName;
}


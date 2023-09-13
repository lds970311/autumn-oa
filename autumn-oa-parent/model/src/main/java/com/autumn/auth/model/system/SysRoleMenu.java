package com.autumn.auth.model.system;


import com.autumn.auth.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "角色菜单")
@TableName("sys_role_menu")
public class SysRoleMenu extends BaseEntity {
	
	@Serial
	private static final long serialVersionUID = 1L;

	@Schema(title = "角色id")
	@TableField("role_id")
	private Long roleId;

	@Schema(title = "菜单id")
	@TableField("menu_id")
	private Long menuId;

}


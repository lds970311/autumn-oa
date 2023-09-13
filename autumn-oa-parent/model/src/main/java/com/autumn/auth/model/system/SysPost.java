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
@Schema(description = "岗位")
@TableName("sys_post")
public class SysPost extends BaseEntity {

	@Serial
	private static final long serialVersionUID = 1L;

	@Schema(title = "岗位编码")
	@TableField("post_code")
	private String postCode;

	@Schema(title = "岗位名称")
	@TableField("name")
	private String name;

	@Schema(title = "显示顺序")
	@TableField("description")
	private String description;

	@Schema(title = "状态（1正常 0停用）")
	@TableField("status")
	private Integer status;

}
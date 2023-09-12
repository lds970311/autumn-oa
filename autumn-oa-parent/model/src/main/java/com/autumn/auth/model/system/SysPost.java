package com.autumn.auth.model.system;


import com.autumn.auth.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serial;

@Data
@ApiModel(description = "岗位")
@TableName("sys_post")
public class SysPost extends BaseEntity {

	@Serial
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "岗位编码")
	@TableField("post_code")
	private String postCode;

	@ApiModelProperty(value = "岗位名称")
	@TableField("name")
	private String name;

	@ApiModelProperty(value = "显示顺序")
	@TableField("description")
	private String description;

	@ApiModelProperty(value = "状态（1正常 0停用）")
	@TableField("status")
	private Integer status;

}
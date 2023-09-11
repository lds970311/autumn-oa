package com.autumn.model.process;


import com.autumn.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serial;
import java.util.List;

@Data
@ApiModel(description = "ProcessType")
@TableName("oa_process_type")
public class ProcessType extends BaseEntity {

	@Serial
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "类型名称")
	@TableField("name")
	private String name;

	@ApiModelProperty(value = "描述")
	@TableField("description")
	private String description;

	@TableField(exist = false)
	private List<ProcessTemplate> processTemplateList;
}
package com.autumn.auth.model.process;


import com.autumn.auth.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "ProcessType")
@TableName("oa_process_type")
public class ProcessType extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(title = "类型名称")
    @TableField("name")
    private String name;

    @Schema(title = "描述")
    @TableField("description")
    private String description;

    @TableField(exist = false)
    private List<ProcessTemplate> processTemplateList;
}
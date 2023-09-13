package com.autumn.auth.model.process;


import com.autumn.auth.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "ProcessTemplate")
@TableName("oa_process_template")
public class ProcessTemplate extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Schema(title = "模板名称")
    @TableField("name")
    private String name;

    @Schema(title = "图标路径")
    @TableField("icon_url")
    private String iconUrl;

    @Schema(title = "processTypeId")
    @TableField("process_type_id")
    private Long processTypeId;

    @Schema(title = "表单属性")
    @TableField("form_props")
    private String formProps;

    @Schema(title = "表单选项")
    @TableField("form_options")
    private String formOptions;

    @Schema(title = "描述")
    @TableField("description")
    private String description;

    @Schema(title = "流程定义key")
    @TableField("process_definition_key")
    private String processDefinitionKey;

    @Schema(title = "流程定义上传路process_model_id")
    @TableField("process_definition_path")
    private String processDefinitionPath;

    @Schema(title = "流程定义模型id")
    @TableField("process_model_id")
    private String processModelId;

    @Schema(title = "状态")
    @TableField("status")
    private Integer status;

    @TableField(exist = false)
    private String processTypeName;
}
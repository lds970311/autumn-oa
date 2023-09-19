package com.autumn.auth.model.process;


import com.autumn.auth.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;



@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "Process")
@TableName("oa_process")
public class Process extends BaseEntity {


    private static final long serialVersionUID = 1L;

    @Schema(title = "审批code")
    @TableField("process_code")
    private String processCode;

    @Schema(title = "用户id")
    @TableField("user_id")
    private Long userId;

    @Schema(title = "审批模板id")
    @TableField("process_template_id")
    private Long processTemplateId;

    @Schema(title = "审批类型id")
    @TableField("process_type_id")
    private Long processTypeId;

    @Schema(title = "标题")
    @TableField("title")
    private String title;

    @Schema(title = "描述")
    @TableField("description")
    private String description;

    @Schema(title = "表单值")
    @TableField("form_values")
    private String formValues;

    @Schema(title = "流程实例id")
    @TableField("process_instance_id")
    private String processInstanceId;

    @Schema(title = "当前审批人")
    @TableField("current_auditor")
    private String currentAuditor;

    @Schema(title = "状态（0：默认 1：审批中 2：审批通过 -1：驳回）")
    @TableField("status")
    private Integer status;
}
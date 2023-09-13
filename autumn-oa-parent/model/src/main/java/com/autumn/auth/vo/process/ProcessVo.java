package com.autumn.auth.vo.process;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
@Schema(description = "Process")
public class ProcessVo {

    private Long id;

    private Date createTime;

    @Schema(title = "审批code")
    private String processCode;

    @Schema(title = "用户id")
    private Long userId;
    private String name;

    @TableField("process_template_id")
    private Long processTemplateId;
    private String processTemplateName;

    @Schema(title = "审批类型id")
    private Long processTypeId;
    private String processTypeName;

    @Schema(title = "标题")
    private String title;

    @Schema(title = "描述")
    private String description;

    @Schema(title = "表单属性")
    private String formProps;

    @Schema(title = "表单选项")
    private String formOptions;

    @Schema(title = "表单属性值")
    private String formtitles;

    @Schema(title = "流程实例id")
    private String processInstanceId;

    @Schema(title = "当前审批人")
    private String currentAuditor;

    @Schema(title = "状态（0：默认 1：审批中 2：审批通过 -1：驳回）")
    private Integer status;

    private String taskId;

}
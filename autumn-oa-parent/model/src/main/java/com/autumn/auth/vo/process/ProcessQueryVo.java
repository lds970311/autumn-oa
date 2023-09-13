package com.autumn.auth.vo.process;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Process")
public class ProcessQueryVo {

    @Schema(title = "关键字")
    private String keyword;

    @Schema(title = "用户id")
    private Long userId;

    @TableField("process_template_id")
    private Long processTemplateId;

    @Schema(title = "审批类型id")
    private Long processTypeId;

    private String createTimeBegin;
    private String createTimeEnd;

    @Schema(title = "状态（0：默认 1：审批中 2：审批通过 -1：驳回）")
    private Integer status;


}
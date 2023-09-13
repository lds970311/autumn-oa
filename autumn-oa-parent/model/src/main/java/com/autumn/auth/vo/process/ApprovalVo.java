package com.autumn.auth.vo.process;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ApprovalVo {

    private Long processId;

    private String taskId;

    @Schema(title = "状态")
    private Integer status;

    @Schema(title = "审批描述")
    private String description;
}

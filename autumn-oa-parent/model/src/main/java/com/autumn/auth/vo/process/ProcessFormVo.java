package com.autumn.auth.vo.process;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "流程表单")
public class ProcessFormVo {

	@Schema(title = "审批模板id")
	private Long processTemplateId;

	@Schema(title = "审批类型id")
	private Long processTypeId;

	@Schema(title = "表单值")
	private String formtitles;

}
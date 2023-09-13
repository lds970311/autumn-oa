package com.autumn.auth.model.system;


import com.autumn.auth.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "SysOperLog")
@TableName("sys_oper_log")
public class SysOperLog extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Schema(title = "模块标题")
	@TableField("title")
	private String title;

	@Schema(title = "业务类型（0其它 1新增 2修改 3删除）")
	@TableField("business_type")
	private String businessType;

	@Schema(title = "方法名称")
	@TableField("method")
	private String method;

	@Schema(title = "请求方式")
	@TableField("request_method")
	private String requestMethod;

	@Schema(title = "操作类别（0其它 1后台用户 2手机端用户）")
	@TableField("operator_type")
	private String operatorType;

	@Schema(title = "操作人员")
	@TableField("oper_name")
	private String operName;

	@Schema(title = "部门名称")
	@TableField("dept_name")
	private String deptName;

	@Schema(title = "请求URL")
	@TableField("oper_url")
	private String operUrl;

	@Schema(title = "主机地址")
	@TableField("oper_ip")
	private String operIp;

	@Schema(title = "请求参数")
	@TableField("oper_param")
	private String operParam;

	@Schema(title = "返回参数")
	@TableField("json_result")
	private String jsonResult;

	@Schema(title = "操作状态（0正常 1异常）")
	@TableField("status")
	private Integer status;

	@Schema(title = "错误消息")
	@TableField("error_msg")
	private String errorMsg;

	@Schema(title = "操作时间")
	@TableField("oper_time")
	private Date operTime;

}
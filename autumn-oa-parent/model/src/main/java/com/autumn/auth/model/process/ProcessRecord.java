package com.autumn.auth.model.process;


import com.autumn.auth.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;



@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "ProcessRecord")
@TableName("oa_process_record")
public class ProcessRecord extends BaseEntity {


    private static final long serialVersionUID = 1L;

    @Schema(title = "审批流程id")
    @TableField("process_id")
    private Long processId;

    @Schema(title = "审批描述")
    @TableField("description")
    private String description;

    @Schema(title = "状态")
    @TableField("status")
    private Integer status;

    @Schema(title = "操作用户id")
    @TableField("operate_user_id")
    private Long operateUserId;

    @Schema(title = "操作用户")
    @TableField("operate_user")
    private String operateUser;

}
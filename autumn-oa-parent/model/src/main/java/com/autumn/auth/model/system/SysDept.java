package com.autumn.auth.model.system;


import com.autumn.auth.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "部门")
@TableName("sys_dept")
public class SysDept extends BaseEntity {


    private static final long serialVersionUID = 1L;

    @Schema(title = "部门名称")
    @TableField("name")
    private String name;

    @Schema(title = "上级部门id")
    @TableField("parent_id")
    private Long parentId;

    @Schema(title = "树结构")
    @TableField("tree_path")
    private String treePath;

    @Schema(title = "排序")
    @TableField("sort_title")
    private Integer sorttitle;

    @Schema(title = "负责人")
    @TableField("leader")
    private String leader;

    @Schema(title = "电话")
    @TableField("phone")
    private String phone;

    @Schema(title = "状态（1正常 0停用）")
    @TableField("status")
    private Integer status;

    @Schema(title = "下级部门")
    @TableField(exist = false)
    private List<SysDept> children;

}
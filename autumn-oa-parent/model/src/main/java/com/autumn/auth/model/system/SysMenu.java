package com.autumn.auth.model.system;


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
@Schema(description = "菜单")
@TableName("sys_menu")
public class SysMenu extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(title = "所属上级")
    @TableField("parent_id")
    private Long parentId;

    @Schema(title = "名称")
    @TableField("name")
    private String name;

    @Schema(title = "类型(1:菜单,2:按钮)")
    @TableField("type")
    private Integer type;

    @Schema(title = "路由地址")
    @TableField("path")
    private String path;

    @Schema(title = "组件路径")
    @TableField("component")
    private String component;

    @Schema(title = "权限标识")
    @TableField("perms")
    private String perms;

    @Schema(title = "图标")
    @TableField("icon")
    private String icon;

    @Schema(title = "排序")
    @TableField("sort_title")
    private Integer sorttitle;

    @Schema(title = "状态(0:禁止,1:正常)")
    @TableField("status")
    private Integer status;

    // 下级列表
    @TableField(exist = false)
    private List<SysMenu> children;
    //是否选中
    @TableField(exist = false)
    private boolean isSelect;
}


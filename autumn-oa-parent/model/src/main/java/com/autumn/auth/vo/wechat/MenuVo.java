package com.autumn.auth.vo.wechat;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "菜单")
public class MenuVo {

    @Schema(title = "id")
    private Long id;

    @Schema(title = "id")
    private Long parentId;

    @Schema(title = "名称")
    private String name;

    @Schema(title = "类型")
    private String type;

    @Schema(title = "url")
    private String url;

    @Schema(title = "菜单key")
    private String meunKey;

    @Schema(title = "排序")
    private Integer sort;

    @Schema(title = "下级")
    @TableField(exist = false)
    private List<MenuVo> children;

}
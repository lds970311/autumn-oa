package com.autumn.auth.vo.system;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "分配菜单")
@Data
public class AssginMenuVo {

    @Schema(title = "角色id")
    private Long roleId;

    @Schema(title = "菜单id列表")
    private List<Long> menuIdList;

}

package com.autumn.auth.vo.system;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "分配菜单")
@Data
public class AssginRoleVo {

    @Schema(title = "用户id")
    private Long userId;

    @Schema(title = "角色id列表")
    private List<Long> roleIdList;

}

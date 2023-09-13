package com.autumn.auth.vo.wechat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class BindPhoneVo {

    @Schema(title = "手机")
    private String phone;

    @Schema(title = "openId")
    private String openId;
}

package com.autumn.auth.exception;

import com.autumn.result.ResultCodeEnum;
import lombok.Data;

@Data
public class AutumnException extends RuntimeException {
    private Integer code;

    private String message;

    /**
     * 通过状态码和错误消息创建异常对象
     *
     * @param code
     * @param message
     */
    public AutumnException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    /**
     * 接收枚举类型对象
     *
     * @param resultCodeEnum
     */
    public AutumnException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
    }

    @Override
    public String toString() {
        return "AutumnException{" +
                "code=" + code +
                ", message=" + this.getMessage() +
                '}';
    }
}

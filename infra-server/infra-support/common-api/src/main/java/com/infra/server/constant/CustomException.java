package com.infra.server.constant;

import lombok.Data;

/**
 * @author ： zzd
 * @Date ： 15:48 2020/6/25
 * @Description : 自定义全局异常类
 */
@Data
public class CustomException extends RuntimeException {

    private Integer code;

    public CustomException(Integer code,String message) {
        super(message);
        this.code = code;
    }

    public CustomException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }

    @Override
    public String toString() {
        return "CustomException {" + "code=" + code + ", message=" + this.getMessage() + "}";
    }

}

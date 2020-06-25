package com.infra.server.constant;

import lombok.Getter;

/**
 * author: zzd
 * date: 2020/6/24
 * description: 结果类枚举，全局统一结果处理
 */
@Getter
public enum ResultCodeEnum {

    SUCCESS(true, 00 , "成功"),
    UNKNOWN_ERROR(false,01,"未知错误"),
    PARAM_ERROR(false,02,"参数错误"),
    NULL_POINTER(false,03,"空指针异常"),
    NO_FILE_CONTENT(false, 10, "文件内容为空"),
    MAIL_SEND_FAILED(false, 20, "邮件发送失败"),
    CAPTCHA_SEND_FAILED(false, 21, "验证码发送失败");

    private Boolean success; //响应是否成功
    private Integer code; // 响应状态码
    private String message; // 响应信息

    ResultCodeEnum(Boolean success,Integer code,String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

}

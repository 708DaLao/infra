package com.infra.server.api;

import lombok.Getter;

/**
 * author: zzd
 * date: 2020/6/24
 * description: 结果类枚举，全局统一结果处理
 */
@Getter
public enum ResultCodeEnum {

    SUCCESS(true, 0 , "成功"),
    UNKNOWN_ERROR(false,1,"未知错误"),
    PARAM_ERROR(false,2,"参数错误"),
    NULL_POINTER(false,3,"空指针异常"),
    NO_FILE_CONTENT(false, 10, "文件内容为空"),
    MAIL_SEND_FAILED(false, 20, "邮件发送失败"),
    CAPTCHA_SEND_FAILED(false, 21, "验证码发送失败"),
    TOKEN_ERROR(false,401,"token无效或过期!"),
    NO_PERMISSION(false,403,"抱歉，您没有访问权限！"),
    NULL_USERNAME(false,404,"该用户不存在！"),
    ACCOUNT_LOCKED(false,405,"该账号已被锁定！"),
    NULL_CLIENTID(false,404,"该client_id不存在！"),
    REQUEST_TIMEOUT(false,504, "请求超时，请稍后再试！");

    private Boolean success; //响应是否成功
    private Integer code; // 响应状态码
    private String message; // 响应信息

    ResultCodeEnum(Boolean success,Integer code,String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

}

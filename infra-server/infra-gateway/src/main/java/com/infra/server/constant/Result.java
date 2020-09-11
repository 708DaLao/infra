package com.infra.server.constant;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * date: 2020/6/24
 * description: 统一结果类
 * el: return Result.ok().data("itms", list).message("用户列表");
 * @author zzd
 */
@Data
public class Result {

    private Boolean success;

    private Integer code;

    private String message;

    // 返回的数据
    private Map<String,Object> data = new HashMap<String, Object>();

    // 构造器私有
    private Result(){}

    // 通用返回成功
    public static Result ok() {
        Result r = new Result();
        r.setSuccess(ResultCodeEnum.SUCCESS.getSuccess());
        r.setCode(ResultCodeEnum.SUCCESS.getCode());
        r.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return r;
    }

    // 通用返回失败,未知错误
    public static Result error() {
        Result r = new Result();
        r.setSuccess(ResultCodeEnum.UNKNOWN_ERROR.getSuccess());
        r.setCode(ResultCodeEnum.UNKNOWN_ERROR.getCode());
        r.setMessage(ResultCodeEnum.UNKNOWN_ERROR.getMessage());
        return r;
    }

    // 设置结果，形参为结果枚举,自定义返回
    public static Result setResult(ResultCodeEnum result) {
        Result r = new Result();
        r.setSuccess(result.getSuccess());
        r.setCode(result.getCode());
        r.setMessage(result.getMessage());
        return r;
    }

    /**------------使用链式编程，返回类本身-----------**/

    // 自定义返回数据
    public Result data(Map<String,Object> map) {
        this.setData(map);
        return this;
    }

    // 通用设置data
    public Result data(String key,Object value) {
        this.data.put(key, value);
        return this;
    }

    // 自定义状态信息
    public Result message(String message) {
        this.setMessage(message);
        return this;
    }

    // 自定义状态码
    public Result code(Integer code) {
        this.setCode(code);
        return this;
    }

    // 自定义返回结果
    public Result success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

}

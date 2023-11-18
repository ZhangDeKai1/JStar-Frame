package com.zdk.web.entity;

import com.zdk.web.enums.ResultEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * 统一结果返回封装类
 */
@Data
public class Result<T> implements Serializable {

    private Boolean success;

    private Integer code;

    private String message;

    private T data;

    public static Result ok(){
        Result result=new Result();
        result.setSuccess(true);
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMessage(ResultEnum.SUCCESS.getMessage());
        return result;
    }

    public static <T> Result ok(T data){
        Result result=new Result();
        result.setSuccess(true);
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMessage(ResultEnum.SUCCESS.getMessage());
        result.setData(data);
        return result;
    }

    public static Result fail(){
        Result result=new Result();
        result.setSuccess(false);
        result.setCode(ResultEnum.FAIL.getCode());
        result.setMessage(ResultEnum.FAIL.getMessage());
        return result;
    }

    public static <T> Result fail(T data){
        Result result=new Result();
        result.setSuccess(false);
        result.setCode(ResultEnum.FAIL.getCode());
        result.setMessage(ResultEnum.FAIL.getMessage());
        result.setData(data);
        return result;
    }

}

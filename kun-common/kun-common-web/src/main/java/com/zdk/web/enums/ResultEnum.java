package com.zdk.web.enums;


import lombok.Getter;

/**
 * 结果枚举类
 */
@Getter
public enum ResultEnum {
    SUCCESS(200,"成功"),
    FAIL(500,"失败");

    private Integer code;

    private String message;

    ResultEnum(Integer code, String desc) {
        this.code = code;
        this.message = desc;
    }

    public static ResultEnum queryByCode(Integer code){
        for (ResultEnum resultEnum : ResultEnum.values()) {
            if(resultEnum.code==code) {
                return resultEnum;
            }
        }
        return null;
    }
}

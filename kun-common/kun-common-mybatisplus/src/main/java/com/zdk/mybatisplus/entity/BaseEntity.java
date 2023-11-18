package com.zdk.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 公共字段的抽取,让其他实体类去继承这个类
 */
@Data
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = -20972045739696493L;

    @TableField(fill= FieldFill.INSERT)//设置执行insert操作时自动填充
    private String createBy;

    @TableField(fill=FieldFill.INSERT)//设置执行insert操作时自动填充
    private Date createTime;

    @TableField(fill=FieldFill.UPDATE)//设置执行update操作时自动填充
    private String updateBy;

    @TableField(fill=FieldFill.UPDATE)//设置执行update操作时自动填充
    private Date updateTime;

    @TableField(fill = FieldFill.INSERT)//设置执行insert操作时自动填充
    @TableLogic
    private Integer deleteFlag;

    @TableField(fill=FieldFill.INSERT)//设置执行insert操作时自动填充
    private Integer version;
}

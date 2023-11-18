package com.zdk.user.entity.dto;


import com.zdk.mybatisplus.entity.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * (UserDto)实体类
 *
 */
@Data
public class UserDto extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -20972045739696493L;
    
    private Long id;
    
    private String name;
    
    private Integer age;
}


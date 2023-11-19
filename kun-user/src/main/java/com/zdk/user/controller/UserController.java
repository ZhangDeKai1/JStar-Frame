package com.zdk.user.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.zdk.user.converter.UserConverter;
import com.zdk.user.entity.dto.UserDto;
import com.zdk.user.entity.po.UserPo;
import com.zdk.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/add")
    public void add(@RequestBody UserDto userDto){
        try {
            //参数校验
            Preconditions.checkNotNull(userDto.getName(),"用户名不能为空..");

            if(log.isInfoEnabled()){
                log.info("UserController:user:add:dto:{}", JSON.toJSONString(userDto.toString()));
            }
            UserPo userPo = UserConverter.INSTANCE.UserDtoToPo(userDto);

            userService.insert(userPo);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("UserController:user:add:{}",e.getMessage(),e);
        }
    }

}

package com.zdk.user.converter;

import com.zdk.user.entity.dto.UserDto;
import com.zdk.user.entity.po.UserPo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserConverter {
    UserConverter INSTANCE= Mappers.getMapper(UserConverter.class);

    UserPo UserDtoToPo(UserDto userDto);


}

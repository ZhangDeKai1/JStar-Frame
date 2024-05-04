package com.zdk.user.converter;

import com.zdk.user.entity.dto.UserDto;
import com.zdk.user.entity.po.UserPo;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-26T16:21:46+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.12 (Oracle Corporation)"
)
public class UserConverterImpl implements UserConverter {

    @Override
    public UserPo UserDtoToPo(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        UserPo userPo = new UserPo();

        userPo.setCreateBy( userDto.getCreateBy() );
        userPo.setCreateTime( userDto.getCreateTime() );
        userPo.setUpdateBy( userDto.getUpdateBy() );
        userPo.setUpdateTime( userDto.getUpdateTime() );
        userPo.setDeleteFlag( userDto.getDeleteFlag() );
        userPo.setVersion( userDto.getVersion() );

        return userPo;
    }
}

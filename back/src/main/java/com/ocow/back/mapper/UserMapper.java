package com.ocow.back.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.ocow.back.dto.UserDto;
import com.ocow.back.model.User;

@Component
@Mapper(componentModel="spring")
public interface UserMapper extends EntityMapper<UserDto, User> {

}

package com.ocow.back.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.ocow.back.dto.ClientUserDto;
import com.ocow.back.model.ClientUser;

@Component
@Mapper(componentModel="spring")
public interface ClientUserMapper extends EntityMapper<ClientUserDto, ClientUser> {

}

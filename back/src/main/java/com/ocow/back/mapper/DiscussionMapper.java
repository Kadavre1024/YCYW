package com.ocow.back.mapper;

import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ocow.back.dto.DiscussionDto;
import com.ocow.back.model.Discussion;
import com.ocow.back.model.Rental;
import com.ocow.back.service.ClientUserService;
import com.ocow.back.service.RentalService;
import com.ocow.back.model.ClientUser;

@Component
@Mapper(componentModel="spring", uses = {RentalService.class, ClientUserService.class}, imports = {Rental.class, ClientUser.class})
public abstract class DiscussionMapper implements EntityMapper<DiscussionDto, Discussion>{

	@Autowired
    RentalService rentalService;
	
    @Autowired
    ClientUserService clientUserService;
    
    @Autowired
    MessageMapper messageMapper;

    @Mappings({
            @Mapping(target = "clientUser", expression = "java(discDto.getClientUserId() != null ? this.clientUserService.findById(discDto.getClientUserId()) : null)"),
            @Mapping(target = "rental", expression = "java(discDto.getRentalId() != null ? this.rentalService.findById(discDto.getRentalId()) : null)"),
    })
    public abstract Discussion toEntity(DiscussionDto discDto);


    @Mappings({
            @Mapping(source = "disc.clientUser.id", target = "clientUserId"),
            @Mapping(source = "disc.rental.id", target = "rentalId"),
    })
    public abstract DiscussionDto toDto(Discussion disc);
}

package com.ocow.back.mapper;

import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ocow.back.dto.SupportUserDto;
import com.ocow.back.model.Agency;
import com.ocow.back.model.SupportUser;
import com.ocow.back.service.AgencyService;

@Component
@Mapper(componentModel="spring", uses = {AgencyService.class}, imports = {Agency.class})
public abstract class SupportUserMapper implements EntityMapper<SupportUserDto, SupportUser> {

	@Autowired
	AgencyService agencyService;
	
	@Mappings({
        @Mapping(target = "agency", expression = "java(supportUserDto.getAgencyId() != null ? this.agencyService.findById(supportUserDto.getAgencyId()) : null)"),
	})
	public abstract SupportUser toEntity(SupportUserDto supportUserDto);


	@Mappings({
        @Mapping(source = "supportUser.agency.id", target = "agencyId"),
	})
	public abstract SupportUserDto toDto(SupportUser supportUser);
}

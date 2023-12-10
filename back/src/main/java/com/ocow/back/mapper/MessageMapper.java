package com.ocow.back.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ocow.back.dto.MessageDto;
import com.ocow.back.model.ClientUser;
import com.ocow.back.model.Discussion;
import com.ocow.back.model.Message;
import com.ocow.back.model.SupportUser;
import com.ocow.back.service.ClientUserService;
import com.ocow.back.service.DiscussionService;
import com.ocow.back.service.SupportUserService;

@Component
@Mapper(componentModel="spring", uses = {DiscussionService.class, ClientUserService.class, SupportUserService.class}, imports = {Discussion.class, ClientUser.class, SupportUser.class})
public abstract class MessageMapper implements EntityMapper<MessageDto, Message> {

	@Autowired
	DiscussionService discussionService;
	
	@Autowired
	ClientUserService clientUserService;
	
	@Autowired
	SupportUserService supportUserService;
	
	@Mappings({
        @Mapping(target = "discussion", expression = "java(messageDto.getDiscussionId() != null ? this.discussionService.findById(messageDto.getDiscussionId()) : null)"),
        @Mapping(target = "clientUser", expression = "java(messageDto.getClientUserId() != null ? this.clientUserService.findById(messageDto.getClientUserId()) : null)"),
        @Mapping(target = "supportUser", expression = "java(messageDto.getSupportUserId() != null ? this.supportUserService.findById(messageDto.getSupportUserId()) : null)"),
	})
	public abstract Message toEntity(MessageDto messageDto);


	@Mappings({
        @Mapping(source = "message.clientUser.id", target = "clientUserId"),
        @Mapping(source = "message.supportUser.id", target = "supportUserId"),
        @Mapping(source = "message.discussion.id", target = "discussionId"),
	})
	public abstract MessageDto toDto(Message message);
}

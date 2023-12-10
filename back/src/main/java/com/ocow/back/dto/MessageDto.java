package com.ocow.back.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class MessageDto {

	private Long id;
	
	private Long discussionId;
	
	private Long clientUserId;
	
	private Long supportUserId;
	
	private String message;
	
	private LocalDateTime createdAt;
}

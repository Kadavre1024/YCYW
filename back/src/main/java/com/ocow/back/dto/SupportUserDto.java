package com.ocow.back.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class SupportUserDto {

	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	private Long agencyId;
	
	private String email;
	
	private String password;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;
}

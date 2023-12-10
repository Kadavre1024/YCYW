package com.ocow.back.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ClientUserDto {

	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	private LocalDate birthDate;
	
	private String address;
	
	private String email;
	
	private String password;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;
}

package com.ocow.back.payload;

import lombok.Data;

@Data
public class JwtResponse {

	private String token;
	private String type = "Bearer";
	private Long id;
	private String username;
	private String firstName;
	private String laststName;
	private String password;
	
	
	public JwtResponse(String accessToken, String username, Long id, String firstName, String lastName,String password) {
	  this.token = accessToken;
	  this.username = username;
	  this.id = id;
	  this.firstName = firstName;
	  this.laststName = lastName;
	  this.password = password;
	}
}

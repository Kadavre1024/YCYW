package com.ocow.back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ocow.back.dto.SupportUserDto;
import com.ocow.back.mapper.SupportUserMapper;
import com.ocow.back.model.SupportUser;
import com.ocow.back.payload.JwtResponse;
import com.ocow.back.payload.LoginRequest;
import com.ocow.back.payload.MessageResponse;
import com.ocow.back.security.JwtUtil;
import com.ocow.back.security.UserDetailsImpl;
import com.ocow.back.security.UserDetailsServiceImpl;
import com.ocow.back.service.SupportUserService;

@RestController
@RequestMapping("api/support/auth")
public class SupportAuthController {

	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	private SupportUserService supportUserService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private SupportUserMapper userMapper;

	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest){
		
		SupportUser user = supportUserService.findByEmail(loginRequest.getEmail());
		
		if(user == null) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: invalid email"));
		}
		
		if(!encoder.matches(loginRequest.getPassword(), user.getPassword())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: invalid password"));
		}
		
		Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(authentication.getName());
        String jwt = jwtUtil.generateToken(userDetails.getUsername());
        
		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getId(), userDetails.getFirstName(), userDetails.getLastName(), userDetails.getPassword()));
	}
	
	@GetMapping("/me")
	public ResponseEntity<?> getUserDetails(Authentication auth){
		SupportUserDto userAuth = userMapper.toDto(supportUserService.findByEmail(auth.getName()))   ;
		return ResponseEntity.ok().body(userAuth);
	}
}

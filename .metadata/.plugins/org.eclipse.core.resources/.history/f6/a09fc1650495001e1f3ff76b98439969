package com.ocow.back.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ocow.back.model.User;
import com.ocow.back.repository.UserRepository;

public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + email));

	    return UserDetailsImpl
	            .builder()
	            .id(user.getId())
	            .username(user.getEmail())
	            .firstName(user.getFirstName())
	            .lastName(user.getLastName())
	            .password(user.getPassword())
	            .build();
	}
}

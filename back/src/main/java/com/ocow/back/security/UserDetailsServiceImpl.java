package com.ocow.back.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ocow.back.model.ClientUser;
import com.ocow.back.model.SupportUser;
import com.ocow.back.repository.ClientUserRepository;
import com.ocow.back.repository.SupportUserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private ClientUserRepository userRepo;
	
	@Autowired
	private SupportUserRepository supportUserRepo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		if(supportUserRepo.existsByEmail(email)) {
			SupportUser user = supportUserRepo.findByEmail(email).orElse(null); 
			return UserDetailsImpl
		            .builder()
		            .id(user.getId())
		            .username(user.getEmail())
		            .firstName(user.getFirstName())
		            .lastName(user.getLastName())
		            .password(user.getPassword())
		            .authorities(List.of(new SimpleGrantedAuthority("SUPPORT")))
		            .build();
		} else {
			ClientUser user = userRepo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + email));
			return UserDetailsImpl
		            .builder()
		            .id(user.getId())
		            .username(user.getEmail())
		            .firstName(user.getFirstName())
		            .lastName(user.getLastName())
		            .password(user.getPassword())
		            .authorities(List.of(new SimpleGrantedAuthority("USER")))
		            .build();
		}
	}
}

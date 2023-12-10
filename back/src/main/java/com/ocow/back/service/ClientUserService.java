package com.ocow.back.service;

import org.springframework.stereotype.Service;

import com.ocow.back.model.ClientUser;
import com.ocow.back.repository.ClientUserRepository;

@Service
public class ClientUserService {

	private ClientUserRepository userRepo;
	
	public ClientUserService(ClientUserRepository userRepo) {
		this.userRepo = userRepo;
	}
	
	public ClientUser findById(Long userId) {
		return userRepo.findById(userId).orElse(null);
	}

	public void delete(long userId) {
		userRepo.deleteById(userId);
		
	}
	
	public ClientUser findByEmail(String email) {
		return userRepo.findByEmail(email).orElse(null);
	}
	
	public boolean existByEmail(String email) {
		return userRepo.existsByEmail(email);
	}
	
	public void register(ClientUser user) {
		userRepo.save(user);
	}
}

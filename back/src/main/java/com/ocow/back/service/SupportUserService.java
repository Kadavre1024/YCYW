package com.ocow.back.service;

import org.springframework.stereotype.Service;

import com.ocow.back.model.SupportUser;
import com.ocow.back.repository.SupportUserRepository;

@Service
public class SupportUserService {

	private SupportUserRepository userRepo;
	
	public SupportUserService(SupportUserRepository userRepo) {
		this.userRepo = userRepo;
	}
	
	public SupportUser findById(Long userId) {
		return userRepo.findById(userId).orElse(null);
	}

	public void delete(long userId) {
		userRepo.deleteById(userId);
		
	}
	
	public SupportUser findByEmail(String email) {
		return userRepo.findByEmail(email).orElse(null);
	}
	
	public boolean existByEmail(String email) {
		return userRepo.existsByEmail(email);
	}
	
	public void register(SupportUser user) {
		userRepo.save(user);
	}
}

package com.ocow.back.service;

import org.springframework.stereotype.Service;

import com.ocow.back.model.User;
import com.ocow.back.repository.UserRepository;

@Service
public class UserService {

	private UserRepository userRepo;
	
	public UserService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
	
	public User findById(Long userId) {
		return userRepo.findById(userId).orElse(null);
	}

	public void delete(long userId) {
		userRepo.deleteById(userId);
		
	}
	
	public User findByEmail(String email) {
		return userRepo.findByEmail(email).orElse(null);
	}
	
	public boolean existByEmail(String email) {
		return userRepo.existsByEmail(email);
	}
	
	public void register(User user) {
		userRepo.save(user);
	}
}

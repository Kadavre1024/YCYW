package com.ocow.back.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ocow.back.model.Discussion;
import com.ocow.back.repository.DiscussionRepository;

@Service
public class DiscussionService {

	@Autowired
	public DiscussionRepository discRepo;
	
	public DiscussionService(DiscussionRepository discRepo) {
		this.discRepo = discRepo;
	}
	
	public void create(Discussion disc) {
		this.discRepo.save(disc);
	}
	
	public Discussion findById(Long id) {
		return this.discRepo.findById(id).orElse(null);
	}
	
	public List<Discussion> findAllByClientId(Long clientId){
		return this.discRepo.findByClientUserId(clientId);
	}
	
	public List<Discussion> findAll(){
		return this.discRepo.findAll();
	}
}

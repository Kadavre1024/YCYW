package com.ocow.back.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ocow.back.model.Agency;
import com.ocow.back.repository.AgencyRepository;

@Service
public class AgencyService {

	@Autowired
	private AgencyRepository agencyRepo;
	
	public Agency findById(Long id) {
		return agencyRepo.findById(id).orElse(null);
	}
	
	public void delete(long agencyId) {
		agencyRepo.deleteById(agencyId);
	}
	
	public void register(Agency agency) {
		agencyRepo.save(agency);
	}
	
	public List<Agency> findAll(){
		return agencyRepo.findAll();
	}
}

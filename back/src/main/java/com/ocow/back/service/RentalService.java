package com.ocow.back.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ocow.back.model.Rental;
import com.ocow.back.repository.RentalRepository;

@Service
public class RentalService {

	@Autowired
	private RentalRepository rentalRepo;
	
	public RentalService(RentalRepository rentalRepo) {
		this.rentalRepo = rentalRepo;
	}
	
	public List<Rental> findAllByClientUserId(Long clientUserId){
		return rentalRepo.findAllByClientUserId(clientUserId);
	}
	
	public Rental findById(Long id) {
		return rentalRepo.findById(id).orElse(null);
	}
}

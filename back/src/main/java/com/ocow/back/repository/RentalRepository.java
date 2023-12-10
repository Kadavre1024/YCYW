package com.ocow.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ocow.back.model.Rental;

public interface RentalRepository extends JpaRepository<Rental, Long> {

	List<Rental> findAllByClientUserId(Long ClientUserId);
}

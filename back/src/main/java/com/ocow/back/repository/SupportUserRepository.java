package com.ocow.back.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ocow.back.model.SupportUser;

public interface SupportUserRepository extends JpaRepository<SupportUser, Long> {

	Optional<SupportUser> findByEmail(String email);

	Boolean existsByEmail(String email);
}

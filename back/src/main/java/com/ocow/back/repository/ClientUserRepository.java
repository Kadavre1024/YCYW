package com.ocow.back.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ocow.back.model.ClientUser;

import jakarta.transaction.Transactional;


@Transactional
public interface ClientUserRepository extends JpaRepository<ClientUser, Long> {

	Optional<ClientUser> findByEmail(String email);

	Boolean existsByEmail(String email);
}

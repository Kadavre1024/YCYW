package com.ocow.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ocow.back.model.Discussion;

public interface DiscussionRepository extends JpaRepository<Discussion, Long> {

	List<Discussion> findByClientUserId(Long clientUserId);
}

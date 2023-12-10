package com.ocow.back.controller;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ocow.back.dto.DiscussionDto;
import com.ocow.back.mapper.DiscussionMapper;
import com.ocow.back.model.Discussion;
import com.ocow.back.service.ClientUserService;
import com.ocow.back.service.DiscussionService;
import com.ocow.back.service.RentalService;

@RestController
@RequestMapping("chat")
public class DiscussionController {
	
	@Autowired
	private DiscussionService discService;
	
	@Autowired
	private ClientUserService clientUserService;
	
	@Autowired
	private RentalService rentalService;
	
	@Autowired
	private DiscussionMapper discMapper;

	@PostMapping
	public ResponseEntity<?> createChatRoom(@RequestBody @Validated DiscussionDto discussion){
		Discussion newChatRoom = new Discussion(
				clientUserService.findById(discussion.getClientUserId()), 
				discussion.getSubject(),
				rentalService.findById(discussion.getRentalId()));
		
		this.discService.create(newChatRoom);
		return ResponseEntity.accepted().body("New chat room created");
	}
	
	@GetMapping("support/{id}")
	public ResponseEntity<?> getAllChatRoomsByAgencyId(Long id){
		Predicate<Discussion> streamsPredicate = item -> item.rental.agencyStart.getId() == id || item.rental.agencyEnd.getId() == id;
		List<Discussion> agencyDiscussions = this.discService.findAll().stream().filter(streamsPredicate).collect(Collectors.toList());
		return ResponseEntity.ok(discMapper.toDto(agencyDiscussions));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getChatRoomById(@RequestBody @Validated Long id){
		return ResponseEntity.ok(discMapper.toDto(this.discService.findById(id)));
	}
	
	
	
	
}

package com.ocow.back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ocow.back.model.Message;
import com.ocow.back.repository.MessageRepository;

@Service
public class MessageService {

	@Autowired
	private MessageRepository messageRepo;
	
	public Message create(Message message) {
		return this.messageRepo.save(message);
	}

	public Message findAllByDiscussionId(Long id) {
		return this.messageRepo.findAllByDiscussionId(id);
	}

}

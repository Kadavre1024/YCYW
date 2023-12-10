package com.ocow.back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ocow.back.dto.MessageDto;
import com.ocow.back.mapper.MessageMapper;
import com.ocow.back.model.Message;
import com.ocow.back.service.MessageService;

@Controller
@RequestMapping("message")
public class MessageController {

	private final SimpMessagingTemplate template;
	
	@Autowired
	private MessageMapper messageMapper;
	
	@Autowired
	private MessageService messageService;

    MessageController(SimpMessagingTemplate template){
        this.template = template;
    }

    @MessageMapping("/send/{id}")
    public void onReceivedMesage(@DestinationVariable Long id, @Validated MessageDto message){
    	Message newMessage = this.messageService.create(messageMapper.toEntity(message));
    	
        this.template.convertAndSend("/chat/"+id,  newMessage);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getAllMessagesByDiscussionId(@RequestBody @Validated Long id){
    	return ResponseEntity.ok(this.messageMapper.toDto(this.messageService.findAllByDiscussionId(id)));
    }
}

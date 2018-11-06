package com.wep.iftube.controler;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wep.iftube.exception.ResourceNotFoundException;
import com.wep.iftube.model.Chat;
import com.wep.iftube.repositories.ChatRepository;

@RestController
public class ChatController {

	@Autowired
	ChatRepository chatRepository;
	
	@GetMapping("/chat")
	public Page<Chat> getChat(Pageable pageable){
		return chatRepository.findAll(pageable);
	}
	
	@PostMapping("/chat")
	public Chat createChat(@Valid @RequestBody Chat chat) {
		return chatRepository.save(chat);
	}
	
	@PutMapping("/chat/{chatId}")
	public Chat updateChat(@PathVariable Long chatId,
			@Valid @RequestBody Chat chatRequest) {
		return chatRepository.findById(chatId)
				.map(chat -> {
					chat.setTema(chatRequest.getTema());
					
					return chatRepository.save(chat);
				}).orElseThrow(() -> new ResourceNotFoundException("Página não encontrada"
						+ chatId));
	}
	
	@DeleteMapping("/chat/{chatId}")
	public ResponseEntity<?> deletQuestion(@PathVariable Long chatId){
		return chatRepository.findById(chatId)
				.map(chat ->{
					chatRepository.delete(chat);
					return ResponseEntity.ok().build();
				}).orElseThrow(() -> new ResourceNotFoundException("Página não encontrada"
						+ chatId));
	}
	
}
	
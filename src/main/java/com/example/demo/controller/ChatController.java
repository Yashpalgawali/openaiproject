package com.example.demo.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ChatController {

	private final ChatClient chatclient;

	public ChatController(ChatClient.Builder chatClientBuilder) {
		super();
		this.chatclient = chatClientBuilder.build();
	}

	@GetMapping("/chat")
	public String chat(@RequestParam String message) {
		return chatclient.prompt(message).call().content();		 
	}
}

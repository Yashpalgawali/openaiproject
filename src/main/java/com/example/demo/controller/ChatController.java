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
		return chatclient
//				.prompt(message)
				.prompt()
				.system("""
						Your are an internal HR Assistant. Your role is to help\s
						employees with questions related to HR policies, such as \s
						leave policies, working hours, benefits, and code of conduct.
						If a user asks for help with anything outside of these topics.\s
						kindly inform them that you can only assist with queries related to \s HR Policies. 
						
						""")

				.user(message)
				
				.call().content();		 
	}
}

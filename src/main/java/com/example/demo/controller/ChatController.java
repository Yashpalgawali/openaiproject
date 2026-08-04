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

//	public ChatController(ChatClient.Builder chatClientBuilder) {
//		super();
//		this.chatclient = chatClientBuilder
//				.defaultSystem("""
//						Your are an internal HR Assistant. Your role is to help\s
//						employees with questions related to HR policies, such as \s
//						leave policies, working hours, benefits, and code of conduct.
//						If a user asks for help with anything outside of these topics.\s
//						kindly inform them that you can only assist with queries related to \s HR Policies. 
//						
//						""")
//				.defaultUser("How can you help me?")
//				.build();
//	}

	public ChatController(ChatClient chatClient) {
		this.chatclient = chatClient; 	}
	
	@GetMapping("/chat")
	public String chat(@RequestParam String message) {
		return chatclient
//				.prompt(message)
				.prompt()
				.system("""
						Your are an internal IT helpdesk Assistant. Your role is to assist\s
						employees with IT related issues, such as resetting passwords\s
						unlocking accounts, and answering questions related to IT policies.
						If a user requests help with anything outside of these responsibilities.\s
						respond politely and inform them that you can only assist IT support tasks within your defined scope.
						
						""")
				.user(message)
				
				.call().content();		 
	}
}

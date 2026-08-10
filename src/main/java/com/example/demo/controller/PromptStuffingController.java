package com.example.demo.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PromptStuffingController {

	private final ChatClient chatclient;

	@Value("classpath:/promptTemplate/systemPromptTemplate.st")
	private Resource systemPromptTemplate;
	
// 	Without promptTemplate
//	String promptTemplate = """
//				A customer named {customerName} send following message:
//				"{customerMessage}"
//				Write a polite and helpful email response addressing the issue.
//				Maintain a professional tome and provide reassurance.
//
//				Response as if you're writing the email body only. Don't include subject, signature.
//			""";

	public PromptStuffingController(ChatClient chatClient) {
		this.chatclient = chatClient;
	}

	@GetMapping("/prompt-stuffing")
	public String emailResponse(@RequestParam String message) {
		return chatclient
					.prompt()
					.system(systemPromptTemplate)
					.user(message)

				.call().content();
	}
}

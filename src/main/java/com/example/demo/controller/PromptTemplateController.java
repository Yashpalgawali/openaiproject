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
public class PromptTemplateController {

	private final ChatClient chatclient;

	@Value("classpath:/promptTemplate/userPromptTemplate.st")
	private Resource userPromptTemplate;
	
// 	Without promptTemplate
//	String promptTemplate = """
//				A customer named {customerName} send following message:
//				"{customerMessage}"
//				Write a polite and helpful email response addressing the issue.
//				Maintain a professional tome and provide reassurance.
//
//				Response as if you're writing the email body only. Don't include subject, signature.
//			""";

	public PromptTemplateController(ChatClient chatClient) {
		this.chatclient = chatClient;
	}

	@GetMapping("/email")
	public String emailResponse(@RequestParam String customerName, @RequestParam String customerMessage) {
		return chatclient.prompt().system("""
				 You are a professional customer service assistant which helps drafting email responses \n
				 to improve the productivity of the customer support team.

				""")
				.user(promptTemplateSpec-> promptTemplateSpec
//												.text(promptTemplate)
												.text(userPromptTemplate)
												.param("customerName", customerName)
												.param("customerMessage", customerMessage)
												
												)

				.call().content();
	}
}

package com.example.demo.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfig {

	@Bean
	ChatClient chatClient(ChatClient.Builder chatClientBuilder) {
		return chatClientBuilder
				.defaultAdvisors(new SimpleLoggerAdvisor())
				.defaultSystem("""
						Your are an internal HR Assistant. Your role is to help\s
						employees with questions related to HR policies, such as \s
						leave policies, working hours, benefits, and code of conduct.
						If a user asks for help with anything outside of these topics.\s
						kindly inform them that you can only assist with queries related to \s HR Policies. 
						
						""")
				.defaultUser("How can you help me?")
				.build();
	}
}

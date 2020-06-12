package com.dev.course.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.dev.course.services.EmailService;
import com.dev.course.services.SmtpEmailService;

@Configuration
@Profile("prod")
public class ProdConfig {
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;
	
	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}
}

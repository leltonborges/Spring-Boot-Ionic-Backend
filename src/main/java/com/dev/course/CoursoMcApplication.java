package com.dev.course;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoursoMcApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CoursoMcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}
}
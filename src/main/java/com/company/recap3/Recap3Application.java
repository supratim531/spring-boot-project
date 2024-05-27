package com.company.recap3;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Recap3Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Recap3Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("\nSpring Boot Application is up and running...\n");
	}

}

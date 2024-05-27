package com.company.recap3;

import java.util.List;
import java.util.Locale;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.company.recap3.entities.Author;
import com.company.recap3.repositories.AuthorRepository;
import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;

@SpringBootApplication
public class Recap3Application implements CommandLineRunner {

	@Autowired
	private AuthorRepository authorRepository;

	public static void main(String[] args) {
		SpringApplication.run(Recap3Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Faker faker = new Faker();
		FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en-GB"), new RandomService());

		List<Author> authors = IntStream.rangeClosed(1, 100).mapToObj(number -> {
			String email = faker.internet().emailAddress();
			String firstName = faker.name().firstName();
			String lastName = faker.name().lastName();
			String phone = fakeValuesService.regexify("\\d{10}");
			return Author.builder().firstName(firstName).lastName(lastName).email(email).phone(phone).build();
		}).toList();

		this.authorRepository.saveAll(authors);
		System.out.println("\nSpring Boot Application is up and running...\n");
	}

}

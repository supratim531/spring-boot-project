package com.company.recap3;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Recap3Application implements CommandLineRunner {

//	@Autowired
//	private BookRepository bookRepository;
//
//	@Autowired
//	private AuthorRepository authorRepository;

	public static void main(String[] args) {
		SpringApplication.run(Recap3Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		Author amitava = Author.builder().authorId(null).firstName("Amitava").lastName("Chattopadhyay")
//				.email("sonybravia@gmail.com").phone("9163681672").build();
//		amitava = this.authorRepository.save(amitava);
//
//		Book java = Book.builder().title("Complete Reference of Java").author(amitava).build();
//		this.bookRepository.save(java);
//
//		System.out.println("----- 1 Author & 1 Book Saved Successfully -----");

//		this.bookRepository.deleteById(1);
//		amitava.setEmail("sony.xxx@gmail.com");
//		this.bookRepository.save(java);
	}

}

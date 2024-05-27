package com.company.recap3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.recap3.dtos.AuthorDTO;
import com.company.recap3.entities.Author;
import com.company.recap3.services.AuthorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/author")
public class AuthorController {

	@Autowired
	private AuthorService authorService;

	@PostMapping("")
	public ResponseEntity<Author> saveAuthor(@Valid @RequestBody AuthorDTO authorDTO) {
		Author savedAuthor = this.authorService.saveAuthor(authorDTO);
		return new ResponseEntity<>(savedAuthor, HttpStatus.CREATED);
	}

	@GetMapping("")
	public ResponseEntity<?> findAllAuthors(@RequestParam(required = false) String email,
			@RequestParam(required = false) String phone) {
		if (email != null || phone != null) {
			Author author = this.authorService.findAuthorByEmailOrPhone(email, phone);
			return new ResponseEntity<Author>(author, HttpStatus.OK);
		}

		List<Author> authors = this.authorService.findAllAuthors();
		return new ResponseEntity<List<Author>>(authors, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Author> findAuthorById(@PathVariable("id") Integer authorId) {
		Author author = this.authorService.findAuthorById(authorId);
		return new ResponseEntity<>(author, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Author> updateAuthorById(@PathVariable("id") Integer authorId,
			@Valid @RequestBody AuthorDTO authorDTO) {
		Author updatedAuthor = this.authorService.updateAuthorById(authorId, authorDTO);
		return new ResponseEntity<>(updatedAuthor, HttpStatus.CREATED);
	}

	@DeleteMapping("")
	public ResponseEntity<Void> deleteAllAuthors() {
		this.authorService.deleteAllAuthors();
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteAuthorById(@PathVariable("id") Integer authorId) {
		this.authorService.deleteAuthorById(authorId);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

	@GetMapping("/email")
	public ResponseEntity<List<Author>> findAllAuthorsByEmailPattern(@RequestParam String pattern) {
		List<Author> authors = this.authorService.findAllAuthorsByEmailPattern(pattern);
		return new ResponseEntity<>(authors, HttpStatus.OK);
	}

}

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

import com.company.recap3.dtos.BookDTO;
import com.company.recap3.entities.Book;
import com.company.recap3.services.BookService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/book")
public class BookController {

	@Autowired
	private BookService bookService;

	@PostMapping("")
	public ResponseEntity<Book> saveBook(@RequestParam Integer authorId, @Valid @RequestBody BookDTO bookDTO) {
		Book savedBook = this.bookService.saveBook(authorId, bookDTO);
		return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
	}

	@GetMapping("")
	public ResponseEntity<List<Book>> findAllBooks(@RequestParam(required = false) String title) {
		List<Book> books = (title == null) ? this.bookService.findAllBooks()
				: this.bookService.findBookByTitleContaining(title);

		return new ResponseEntity<>(books, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Book> findBookById(@PathVariable("id") Integer bookId) {
		Book book = this.bookService.findBookById(bookId);
		return new ResponseEntity<>(book, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Book> updateBookById(@PathVariable("id") Integer bookId,
			@RequestParam(required = false) Integer authorId, @Valid @RequestBody BookDTO bookDTO) {
		Book updatedBook = this.bookService.updateBookById(bookId, authorId, bookDTO);
		return new ResponseEntity<>(updatedBook, HttpStatus.CREATED);
	}

	@DeleteMapping("")
	public ResponseEntity<Void> deleteAllBooks() {
		this.bookService.deleteAllBooks();
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBookById(@PathVariable("id") Integer bookId) {
		this.bookService.deleteBookById(bookId);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

	@GetMapping("/priceRange")
	public ResponseEntity<List<Book>> findBookByPriceBetween(@RequestParam Long start, @RequestParam Long end) {
		List<Book> books = this.bookService.findBookByPriceBetween(start, end);
		return new ResponseEntity<>(books, HttpStatus.OK);
	}

}

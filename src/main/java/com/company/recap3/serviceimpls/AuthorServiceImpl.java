package com.company.recap3.serviceimpls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.recap3.dtos.AuthorDTO;
import com.company.recap3.entities.Author;
import com.company.recap3.entities.Book;
import com.company.recap3.exceptions.EntityNotFoundException;
import com.company.recap3.mapper.AuthorMapper;
import com.company.recap3.repositories.AuthorRepository;
import com.company.recap3.repositories.BookRepository;
import com.company.recap3.services.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {

	@Autowired
	private AuthorRepository authorRepository;

	@Autowired
	private BookRepository bookRepository;

	@Override
	public Author saveAuthor(AuthorDTO authorDTO) {
		Author author = AuthorMapper.convertToAuthor(authorDTO);
		return this.authorRepository.save(author);
	}

	@Override
	public List<Author> findAllAuthors() {
		List<Author> authors = this.authorRepository.findAll();

		if (authors.size() == 0) {
			throw EntityNotFoundException.builder().errorMessage("No author exists").build();
		}

		return authors;
	}

	@Override
	public Author findAuthorById(Integer authorId) {
		return this.authorRepository.findById(authorId).orElseThrow(
				() -> EntityNotFoundException.builder().errorMessage("No author found with id " + authorId).build());
	}

	@Override
	public Author updateAuthorById(Integer authorId, AuthorDTO authorDTO) {
		Author oldAuthor = this.findAuthorById(authorId);
		Author author = AuthorMapper.convertToAuthor(authorDTO);

		oldAuthor.setFirstName(author.getFirstName());
		oldAuthor.setLastName(author.getLastName());
		oldAuthor.setEmail(author.getEmail());
		oldAuthor.setPhone(author.getPhone());

		return this.authorRepository.save(oldAuthor);
	}

	@Override
	public void deleteAllAuthors() {
		this.findAllAuthors().forEach((author) -> {
			this.deleteAuthorById(author.getAuthorId());
		});
	}

	@Override
	public void deleteAuthorById(Integer authorId) {
		Author savedAuthor = this.findAuthorById(authorId);
		Book attachedBook = savedAuthor.getBook();

		if (attachedBook != null) {
			savedAuthor.setBook(null);
			attachedBook.setAuthor(null);
			this.bookRepository.save(attachedBook);
		}

		this.authorRepository.delete(savedAuthor);
	}

	@Override
	public Author findAuthorByEmailOrPhone(String email, String phone) {
		String errorMessage;

		if (email != null && phone != null) {
			errorMessage = "No author found with both email & phone" + email + " & " + phone;
		} else if (email != null) {
			errorMessage = "No author found with email " + email;
		} else {
			errorMessage = "No author found with phone " + phone;
		}

		final String finalErrorMessage = errorMessage;

		return this.authorRepository.findByEmailOrPhone(email, phone)
				.orElseThrow(() -> EntityNotFoundException.builder().errorMessage(finalErrorMessage).build());
	}

	@Override
	public List<Author> findAllAuthorsByEmailPattern(String pattern) {
		List<Author> authors = this.authorRepository.findAllByEmailPattern(pattern);

		if (authors.size() == 0) {
			throw EntityNotFoundException.builder()
					.errorMessage("No authors found with this email pattern '" + pattern + "'").build();
		}

		return authors;
	}

}

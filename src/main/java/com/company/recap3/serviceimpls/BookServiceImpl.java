package com.company.recap3.serviceimpls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.company.recap3.dtos.BookDTO;
import com.company.recap3.entities.Author;
import com.company.recap3.entities.Book;
import com.company.recap3.exceptions.EntityNotFoundException;
import com.company.recap3.mappers.BookMapper;
import com.company.recap3.repositories.BookRepository;
import com.company.recap3.services.AuthorService;
import com.company.recap3.services.BookService;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private AuthorService authorService;

	@Override
	public Book saveBook(Integer authorId, BookDTO bookDTO) {
		Book book = BookMapper.convertToBook(bookDTO);
		Author author = this.authorService.findAuthorById(authorId);
		book.setAuthor(author);
		author.setBook(book);

		return this.bookRepository.save(book);
	}

	@Override
	public List<Book> findAllBooks() {
		List<Book> books = this.bookRepository.findAll();

		if (books.size() == 0) {
			throw EntityNotFoundException.builder().errorMessage("No book exists").build();
		}

		return books;
	}

	@Override
	public List<Book> findAllBooksByOrderBy(String property, String direction) {
		Sort sort = Sort.by(Sort.Direction.fromString(direction), property);
		List<Book> books = this.bookRepository.findAll(sort);

		if (books.size() == 0) {
			throw EntityNotFoundException.builder().errorMessage("No books found").build();
		}

		return books;
	}

	@Override
	public Book findBookById(Integer bookId) {
		return this.bookRepository.findById(bookId).orElseThrow(
				() -> EntityNotFoundException.builder().errorMessage("Book not found with id " + bookId).build());
	}

	@Override
	public Book updateBookById(Integer bookId, Integer authorId, BookDTO bookDTO) {
		Book oldBook = this.findBookById(bookId);
		Author attachedAuthor = oldBook.getAuthor();
		Book book = BookMapper.convertToBook(bookDTO);

		oldBook.setTitle(book.getTitle());
		oldBook.setPrice(book.getPrice());
		oldBook.setReview(book.getReview());
		oldBook.setReleasedYear(book.getReleasedYear());

		if (authorId == null) {
		} else {
			Author author = this.authorService.findAuthorById(authorId);

			if (author.equals(attachedAuthor)) {
			} else {
				oldBook.setAuthor(author);
				author.setBook(oldBook);

				if (attachedAuthor != null) {
					attachedAuthor.setBook(null);
				}
			}
		}

		return this.bookRepository.save(oldBook);
	}

	@Override
	public void deleteAllBooks() {
		this.bookRepository.deleteAll(this.findAllBooks());
	}

	@Override
	public void deleteBookById(Integer bookId) {
		Book savedBook = this.findBookById(bookId);
		this.bookRepository.delete(savedBook);
	}

	@Override
	public List<Book> findBookByTitleContaining(String title) {
		List<Book> books = this.bookRepository.findByTitleContaining(title);

		if (books.size() == 0) {
			throw EntityNotFoundException.builder().errorMessage("No book exists which contains '" + title + "'")
					.build();
		}

		return books;
	}

	@Override
	public List<Book> findBookByPriceBetween(Long startPrice, Long endPrice) {
		List<Book> books = this.bookRepository.findByPriceBetween(startPrice, endPrice);

		if (books.size() == 0) {
			throw EntityNotFoundException.builder()
					.errorMessage("No books found between rupees " + startPrice + " to " + endPrice + " price range")
					.build();
		}

		return books;
	}

	@Override
	public List<Book> findBookByReleasedYearBetween(String startYear, String endYear) {
		List<Book> books = this.bookRepository.findByReleasedYearBetween(startYear, endYear);

		if (books.size() == 0) {
			throw EntityNotFoundException.builder()
					.errorMessage("No books found between " + startYear + " and " + endYear + " timeline").build();
		}

		return books;
	}

}

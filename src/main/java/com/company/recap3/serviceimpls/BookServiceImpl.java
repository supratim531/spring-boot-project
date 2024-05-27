package com.company.recap3.serviceimpls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.recap3.dtos.BookDTO;
import com.company.recap3.entities.Author;
import com.company.recap3.entities.Book;
import com.company.recap3.exceptions.EntityNotFoundException;
import com.company.recap3.exceptions.RequestBodyPropertyException;
import com.company.recap3.mapper.BookMapper;
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
	public Book saveBook(BookDTO bookDTO) {
		Book book = BookMapper.convertToBook(bookDTO);

		if (book.getAuthor() == null || book.getAuthor().getAuthorId() == null) {
			throw new RequestBodyPropertyException("ERR2002-108",
					"authorId not provided for author (eg. 'author':{'authorId':1})");
		}

		Author author = this.authorService.findAuthorById(book.getAuthor().getAuthorId());

		book.setAuthor(author);
		author.setBook(book);
		return this.bookRepository.save(book);
	}

	@Override
	public List<Book> findAllBooks() {
		List<Book> books = this.bookRepository.findAll();

		if (books.size() == 0) {
			throw new EntityNotFoundException("ERR2002-420", "No book exists");
		}

		return books;
	}

	@Override
	public Book findBookById(Integer bookId) {
		return this.bookRepository.findById(bookId)
				.orElseThrow(() -> new EntityNotFoundException("ERR2002-420", "Book not found with id " + bookId));
	}

	@Override
	public List<Book> findBookByTitleContaining(String title) {
		List<Book> books = this.bookRepository.findByTitleContaining(title);

		if (books.size() == 0) {
			throw new EntityNotFoundException("ERR2002-420", "No book exists which contains '" + title + "'");
		}

		return books;
	}

	@Override
	public Book updateBookById(Integer bookId, BookDTO bookDTO) {
		Book oldBook = this.findBookById(bookId);
		Author oldAuthor = oldBook.getAuthor();

		Book book = BookMapper.convertToBook(bookDTO);

		if (book.getAuthor() == null || book.getAuthor().getAuthorId() == null) {
			throw new RequestBodyPropertyException("ERR2002-108",
					"authorId not provided for author (eg. 'author':{'authorId':1})");
		}

		Author author = this.authorService.findAuthorById(book.getAuthor().getAuthorId());

		oldBook.setTitle(book.getTitle());
		oldBook.setPrice(book.getPrice());
		oldBook.setReview(book.getReview());
		oldBook.setReleasedYear(book.getReleasedYear());

		if (author.equals(oldAuthor)) {
			System.out.println("Author matched");
		} else {
			System.out.println("Author didn't matched");

			if (oldAuthor != null) {
				oldAuthor.setBook(null);
			}

			oldBook.setAuthor(author);
			author.setBook(oldBook);
		}

		return this.bookRepository.save(oldBook);
	}

	@Override
	public void deleteAllBooks() {
		this.bookRepository.deleteAll();
	}

	@Override
	public void deleteBookById(Integer bookId) {
		Book savedBook = this.findBookById(bookId);
		this.bookRepository.delete(savedBook);
	}

}

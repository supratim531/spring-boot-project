package com.company.recap3.services;

import java.util.List;

import com.company.recap3.dtos.BookDTO;
import com.company.recap3.entities.Book;

public interface BookService {

	Book saveBook(BookDTO bookDTO);

	List<Book> findAllBooks();

	Book findBookById(Integer bookId);

	List<Book> findBookByTitleContaining(String title);

	Book updateBookById(Integer bookId, BookDTO bookDTO);

	void deleteAllBooks();

	void deleteBookById(Integer bookId);

}

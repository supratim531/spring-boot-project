package com.company.recap3.services;

import java.util.List;

import com.company.recap3.dtos.BookDTO;
import com.company.recap3.entities.Book;

public interface BookService {

	Book saveBook(Integer authorId, BookDTO bookDTO);

	List<Book> findAllBooks();

	Book findBookById(Integer bookId);

	Book updateBookById(Integer bookId, Integer authorId, BookDTO bookDTO);

	void deleteAllBooks();

	void deleteBookById(Integer bookId);

	List<Book> findBookByTitleContaining(String title);

	List<Book> findBookByPriceBetween(Long startPrice, Long endPrice);

	List<Book> findBookByReleasedYearBetween(String startYear, String endYear);

}

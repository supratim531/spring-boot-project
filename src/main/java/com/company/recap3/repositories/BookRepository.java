package com.company.recap3.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.ListCrudRepository;

import com.company.recap3.entities.Book;

public interface BookRepository extends ListCrudRepository<Book, Integer> {

	List<Book> findAll(Sort sort);

	List<Book> findByTitleContaining(String title);

	List<Book> findByPriceBetween(Long startPrice, Long endPrice);

	List<Book> findByReleasedYearBetween(String startYear, String endYear);

}

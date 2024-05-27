package com.company.recap3.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.company.recap3.entities.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

	List<Book> findAll(Sort sort);

	List<Book> findByTitleContaining(String title);

	List<Book> findByPriceBetween(Long start, Long end);

	List<Book> findByReleasedYearBetween(String start, String end);

}

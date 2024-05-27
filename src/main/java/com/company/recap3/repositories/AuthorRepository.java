package com.company.recap3.repositories;

import java.util.Optional;

import org.springframework.data.repository.ListCrudRepository;

import com.company.recap3.entities.Author;

public interface AuthorRepository extends ListCrudRepository<Author, Integer> {

	Optional<Author> findByEmail(String email);

	Optional<Author> findByPhone(String phone);

}

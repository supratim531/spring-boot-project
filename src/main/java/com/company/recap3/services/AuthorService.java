package com.company.recap3.services;

import java.util.List;

import com.company.recap3.dtos.AuthorDTO;
import com.company.recap3.entities.Author;

public interface AuthorService {

	Author saveAuthor(AuthorDTO authorDTO);

	List<Author> findAllAuthors();

	Author findAuthorById(Integer authorId);

	Author updateAuthorById(Integer authorId, AuthorDTO authorDTO);

	void deleteAllAuthors();

	void deleteAuthorById(Integer authorId);

}

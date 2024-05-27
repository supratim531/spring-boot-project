package com.company.recap3.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import com.company.recap3.entities.Author;

public interface AuthorRepository extends ListCrudRepository<Author, Integer> {

	Optional<Author> findByEmailOrPhone(String email, String phone);

	@Query(value = "SELECT * FROM recap3_author WHERE author_email LIKE %:pattern%", nativeQuery = true)
	List<Author> findAllByEmailPattern(@Param("pattern") String pattern);

}

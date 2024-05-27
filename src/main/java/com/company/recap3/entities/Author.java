package com.company.recap3.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "recap3_author")
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "author_id")
	private Integer authorId;

	@Column(name = "author_first_name")
	private String firstName;

	@Column(name = "author_last_name")
	private String lastName;

	@Column(name = "author_email", unique = true)
	private String email;

	@Column(name = "author_phone", unique = true)
	private String phone;

	@OneToOne(mappedBy = "author")
	private Book book;

}

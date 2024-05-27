package com.company.recap3.dtos;

import com.company.recap3.entities.Book;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDTO {

	@Size(min = 2, max = 100, message = "First name must be between 2 and 100 characters long")
	@NotBlank(message = "First name cannot be empty or null")
	private String firstName;

	@Size(min = 2, max = 100, message = "Last name must be between 2 and 100 characters long")
	@NotBlank(message = "Last name cannot be empty or null")
	private String lastName;

	@Email(message = "Email must be a valid email address")
	@NotBlank(message = "Email cannot be empty or null")
	private String email;

	@Pattern(regexp = "^\\d{10}$", message = "Phone number must have only 10 digits")
	@NotBlank(message = "Phone number cannot be empty or null")
	private String phone;

	private Book book;

}

package com.company.recap3.dtos;

import com.company.recap3.entities.Author;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class BookDTO {

	@Size(max = 255, message = "Maximum length of title should not exceed 255 characters")
	@NotBlank(message = "Title cannot be empty or null")
	private String title;

	@NotNull(message = "Price cannot be null")
	@Min(value = 5, message = "Price cannot be less than 5 rupees")
	private Long price;

	@Min(value = 0, message = "Minimum review point is 0")
	@Max(value = 10, message = "Maximum review point is 10")
	private Integer review;

	@NotBlank(message = "Release year cannot be empty or null")
	@Pattern(regexp = "^[1-9]\\d{3}$", message = "Must be a valid year")
	private String releasedYear;

	private Author author;

}

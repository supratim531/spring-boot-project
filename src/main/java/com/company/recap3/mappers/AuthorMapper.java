package com.company.recap3.mappers;

import org.modelmapper.ModelMapper;

import com.company.recap3.dtos.AuthorDTO;
import com.company.recap3.entities.Author;

public class AuthorMapper {

	private static final ModelMapper modelMapper = new ModelMapper();

	public static Author convertToAuthor(AuthorDTO authorDTO) {
		return modelMapper.map(authorDTO, Author.class);
	}

	public static AuthorDTO convertToAuthorDTO(Author author) {
		return modelMapper.map(author, AuthorDTO.class);
	}

}

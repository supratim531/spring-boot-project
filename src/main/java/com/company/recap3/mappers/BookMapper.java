package com.company.recap3.mappers;

import org.modelmapper.ModelMapper;

import com.company.recap3.dtos.BookDTO;
import com.company.recap3.entities.Book;

public class BookMapper {

	private static final ModelMapper modelMapper = new ModelMapper();

	public static Book convertToBook(BookDTO bookDTO) {
		return modelMapper.map(bookDTO, Book.class);
	}

	public static BookDTO convertToBookDTO(Book book) {
		return modelMapper.map(book, BookDTO.class);
	}

}

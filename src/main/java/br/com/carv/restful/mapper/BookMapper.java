package br.com.carv.restful.mapper;

import br.com.carv.restful.model.Book;
import br.com.carv.restful.model.dto.request.BookRequest;
import br.com.carv.restful.model.dto.request.BookUpdateRequest;
import br.com.carv.restful.model.dto.response.BookResponse;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {
    private final ModelMapper mapper;

    public BookMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public Book toBook(BookRequest bookRequest) {
        return mapper.map(bookRequest, Book.class);
    }

    public Book toBook(BookUpdateRequest bookUpdateRequest) {
        return mapper.map(bookUpdateRequest, Book.class);
    }

    public BookResponse toBookResponse(Book book) {
        return mapper.map(book, BookResponse.class);
    }

}

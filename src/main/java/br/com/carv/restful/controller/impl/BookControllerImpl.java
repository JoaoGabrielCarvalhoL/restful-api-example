package br.com.carv.restful.controller.impl;

import br.com.carv.restful.controller.BookController;
import br.com.carv.restful.model.dto.request.BookRequest;
import br.com.carv.restful.model.dto.request.BookUpdateRequest;
import br.com.carv.restful.model.dto.response.BookResponse;
import br.com.carv.restful.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/book")
@RestController
public class BookControllerImpl implements BookController {

    private final BookService bookService;

    public BookControllerImpl(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public BookResponse save(BookRequest bookRequest) {
        return bookService.save(bookRequest);
    }

    @Override
    public BookResponse update(BookUpdateRequest bookUpdateRequest) {
        return bookService.update(bookUpdateRequest);
    }

    @Override
    public BookResponse findById(Long id) {
        return bookService.findById(id);
    }

    @Override
    public List<BookResponse> findAll() {
        return bookService.findAll();
    }

    @Override
    public Page<BookResponse> findAllPaginated(Pageable pageable) {
        return bookService.findAllPaginated(pageable);
    }

    @Override
    public void delete(Long id) {
        bookService.delete(id);
    }
}

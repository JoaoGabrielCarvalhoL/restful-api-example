package br.com.carv.restful.service.impl;

import br.com.carv.restful.controller.impl.BookControllerImpl;
import br.com.carv.restful.exception.ResourceNotFoundException;
import br.com.carv.restful.mapper.BookMapper;
import br.com.carv.restful.model.Book;
import br.com.carv.restful.model.dto.request.BookRequest;
import br.com.carv.restful.model.dto.request.BookUpdateRequest;
import br.com.carv.restful.model.dto.response.BookResponse;
import br.com.carv.restful.repository.BookRepository;
import br.com.carv.restful.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final Logger logger = Logger.getLogger(BookServiceImpl.class.getSimpleName());
    private final BookMapper mapper;

    public BookServiceImpl(BookRepository bookRepository, BookMapper mapper) {
        this.bookRepository = bookRepository;
        this.mapper = mapper;
    }

    @Override
    public BookResponse findById(Long id) {
        logger.info("Getting book by id: " + id);
        BookResponse bookResponse = bookRepository.findById(id).map(mapper::toBookResponse).orElseThrow(() -> new ResourceNotFoundException("book.not.found"));
        bookResponse.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookControllerImpl.class).findById(id)).withSelfRel());
        return bookResponse;
    }

    @Override
    public BookResponse save(BookRequest bookRequest) {
        logger.info("Saving book into database");
        Book book = mapper.toBook(bookRequest);
        book.setLaunchDate(LocalDateTime.now());
        book = bookRepository.save(book);
        BookResponse bookResponse = mapper.toBookResponse(book);
        bookResponse.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookControllerImpl.class).findById(bookResponse.getId())).withSelfRel());
        return bookResponse;
    }

    @Override
    public BookResponse update(BookUpdateRequest bookUpdateRequest) {
        logger.info("Updating book into database");
        Book book = mapper.toBook(bookUpdateRequest);
        book.setLaunchDate(LocalDateTime.now());
        book = bookRepository.save(book);
        BookResponse bookResponse = mapper.toBookResponse(book);
        bookResponse.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookControllerImpl.class).findById(bookResponse.getId())).withSelfRel());
        return bookResponse;
    }

    @Override
    public List<BookResponse> findAll() {
        List<BookResponse> bookResponses = bookRepository.findAll().stream().map(mapper::toBookResponse).collect(Collectors.toList());
        bookResponses.forEach(book -> book.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookControllerImpl.class).findById(book.getId())).withSelfRel()));
        return bookResponses;
    }

    @Override
    public Page<BookResponse> findAllPaginated(Pageable pageable) {
        List<BookResponse> bookResponses = bookRepository.findAll().stream().map(mapper::toBookResponse).collect(Collectors.toList());
        return new PageImpl<BookResponse>(bookResponses, pageable, bookResponses.size());
    }

    @Override
    public void delete(Long id) {
        logger.info("Deleting book by id: " + id);
        bookRepository.delete(findByIdEntity(id));
    }

    private Book findByIdEntity(Long id){
        return bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("book.not.found"));
    }

}

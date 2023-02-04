package br.com.carv.restful.service;

import br.com.carv.restful.model.dto.request.BookRequest;
import br.com.carv.restful.model.dto.request.BookUpdateRequest;
import br.com.carv.restful.model.dto.response.BookResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {

    BookResponse findById(final Long id);

    BookResponse save(final BookRequest bookRequest);

    BookResponse update(final BookUpdateRequest bookUpdateRequest);

    List<BookResponse> findAll();

    Page<BookResponse> findAllPaginated(final Pageable pageable);

    void delete(final Long id);


}

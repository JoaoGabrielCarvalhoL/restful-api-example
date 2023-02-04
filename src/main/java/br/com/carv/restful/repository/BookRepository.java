package br.com.carv.restful.repository;

import br.com.carv.restful.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}

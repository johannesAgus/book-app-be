package org.javan.service;

import org.javan.domain.Book;
import org.javan.dto.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookService {

    Book save(BookDto bookDto);

    Book update(BookDto bookDto);

    void delete(String id);

    List<Book> findListBook();

    Optional<Book> findByIdBook(String id);

}

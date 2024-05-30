package org.javan.service.impl;

import org.javan.domain.Book;
import org.javan.dto.BookDto;
import org.javan.repository.BookRepository;
import org.javan.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book save(BookDto bookDto) {
        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setDescription(bookDto.getDescription());
        book.setRating(bookDto.getRating());
        book.setAuthor(bookDto.getAuthor());
        book.setIsbn(bookDto.getIsbn());
        book.setCreatedAt(OffsetDateTime.now());

        return bookRepository.save(book);
    }

    @Override
    public Book update(BookDto bookDto) {
        Book book = bookRepository.findById(bookDto.getId()).orElseThrow(() -> new RuntimeException("No Book Id found with id : " + bookDto.getId()));
        book.setTitle(bookDto.getTitle());
        book.setDescription(bookDto.getDescription());
        book.setRating(bookDto.getRating());
        book.setAuthor(bookDto.getAuthor());
        book.setIsbn(bookDto.getIsbn());
        book.setUpdatedAt(OffsetDateTime.now());

        return bookRepository.save(book);
    }

    @Override
    public void delete(String id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("No Book Id found with id : " + id));
        bookRepository.delete(book);
    }

    @Override
    public List<Book> findListBook() {
        return bookRepository.findAllByOrderByTitleAsc();
    }

    @Override
    public Optional<Book> findByIdBook(String id) {
        return bookRepository.findById(id);
    }
}

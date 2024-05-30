package org.javan.web.rest;

import org.javan.domain.Book;
import org.javan.dto.BookDto;
import org.javan.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/book/")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("{id}")
    public BookDto findByMovie(@PathVariable(name = "id") String id) {
        return toDto(bookService.findByIdBook(id).orElseThrow(() -> new RuntimeException("No Book found with id : " + id)));
    }

    @GetMapping("")
    public List<BookDto> findList() {
        return bookService.findListBook().stream().map(this::toDto).collect(Collectors.toList());
    }

    @PostMapping("")
    public BookDto save(@RequestBody @Valid BookDto bookDto) {
        return toDto(bookService.save(bookDto));
    }

    @PatchMapping("")
    public BookDto update(@RequestBody @Valid BookDto bookDto) {
        return toDto(bookService.update(bookDto));
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(name = "id") String id) {
        bookService.delete(id);
    }

    private BookDto toDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setTitle(book.getTitle());
        bookDto.setDescription(book.getDescription());
        bookDto.setRating(book.getRating());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setIsbn(book.getIsbn());
        bookDto.setCreatedAt(book.getCreatedAt().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        if (null != book.getUpdatedAt()){
            bookDto.setUpdatedAt(book.getUpdatedAt().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        }
        return bookDto;
    }

}

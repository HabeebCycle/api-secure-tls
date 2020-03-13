package com.habeebcycle.apisecuretls.controller;

import com.habeebcycle.apisecuretls.model.Book;
import com.habeebcycle.apisecuretls.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping
    public Iterable<Book> getBooks(){
        return bookService.getBooks();
    }

    @PostMapping
    public Book createBook(@RequestBody Book book){
        return bookService.addBook(book);
    }

    @GetMapping("/{bookId}")
    public Book getBook(@PathVariable Long bookId){
        return bookService.getBook(bookId);
    }

    @DeleteMapping
    public void deleteBook(@PathVariable Long bookId){
        bookService.deleteBook(bookId);
    }
}

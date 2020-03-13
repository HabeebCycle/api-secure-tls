package com.habeebcycle.apisecuretls.service;

import com.habeebcycle.apisecuretls.model.Book;
import com.habeebcycle.apisecuretls.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public Iterable<Book> getBooks(){
        return bookRepository.findAll();
    }

    public Book addBook(Book book){
        return bookRepository.save(book);
    }

    public Book getBook(Long bookId){
        return bookRepository.findById(bookId).orElseThrow(RuntimeException::new);
    }

    public void deleteBook(Long bookId){
        bookRepository.deleteById(bookId);
    }
}

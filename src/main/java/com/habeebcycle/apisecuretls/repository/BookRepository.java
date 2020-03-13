package com.habeebcycle.apisecuretls.repository;


import com.habeebcycle.apisecuretls.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}

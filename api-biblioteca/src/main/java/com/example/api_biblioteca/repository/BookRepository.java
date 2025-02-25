package com.example.api_biblioteca.repository;

import com.example.api_biblioteca.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepository extends JpaRepository<Book, Long> {
}
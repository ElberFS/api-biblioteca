package com.example.api_biblioteca.infrastructure.adapter;

import com.example.api_biblioteca.application.port.out.BookRepository;
import com.example.api_biblioteca.domain.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Adaptador JPA para la entidad Book.
 * Implementa BookRepository y extiende JpaRepository para interactuar con la base de datos.
 */
@Repository
public interface BookJpaAdapter extends BookRepository, JpaRepository<Book, Long> {
}

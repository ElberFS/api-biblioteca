package com.example.api_biblioteca.infrastructure.repository;

import com.example.api_biblioteca.domain.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio JPA para la entidad Book.
 * Extiende JpaRepository para proporcionar métodos CRUD automáticos.
 */
@Repository
public interface BookJpaRepository extends JpaRepository<Book, Long> {
}

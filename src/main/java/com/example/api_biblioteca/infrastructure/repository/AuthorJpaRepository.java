package com.example.api_biblioteca.infrastructure.repository;

import com.example.api_biblioteca.domain.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio JPA para la entidad Author.
 * Extiende JpaRepository para proporcionar métodos CRUD automáticos.
 */
@Repository
public interface AuthorJpaRepository extends JpaRepository<Author, Long> {
}

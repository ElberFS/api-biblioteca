package com.example.api_biblioteca.infrastructure.adapter;

import com.example.api_biblioteca.application.port.out.AuthorRepository;
import com.example.api_biblioteca.domain.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Adaptador JPA para la entidad Author.
 * Implementa AuthorRepository y extiende JpaRepository para interactuar con la base de datos.
 */
@Repository
public interface AuthorJpaAdapter extends AuthorRepository, JpaRepository<Author, Long> {
}

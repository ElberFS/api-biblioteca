package com.example.api_biblioteca.repository;

import com.example.api_biblioteca.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
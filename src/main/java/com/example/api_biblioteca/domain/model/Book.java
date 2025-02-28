package com.example.api_biblioteca.domain.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Representa un libro en la biblioteca.
 */
@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

    /**
     * Identificador único del libro.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Título del libro. No puede ser nulo.
     */
    @Column(nullable = false, length = 150)
    private String title;

    /**
     * Género literario del libro (puede ser nulo).
     */
    @Column(length = 50)
    private String genre;

    /**
     * Año de publicación del libro.
     */
    private Integer publishedYear;

    /**
     * Autor del libro. Relación muchos-a-uno con la tabla 'authors'.
     * Se usa `@ManyToOne` para establecer la relación y `@JoinColumn`
     * para definir la clave foránea `author_id` en la tabla 'books'.
     */
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;
}

package com.example.api_biblioteca.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/**
 * Representa un autor en el sistema.
 */
@Entity
@Table(name = "authors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Author {

    /**
     * Identificador único del autor.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre del autor. No puede ser nulo.
     */
    @Column(nullable = false, length = 100)
    private String name;

    /**
     * Fecha de nacimiento del autor (puede ser nula si no se conoce).
     */
    private LocalDate birthdate;

    /**
     * Nacionalidad del autor (máximo 50 caracteres).
     */
    @Column(length = 50)
    private String nationality;
}
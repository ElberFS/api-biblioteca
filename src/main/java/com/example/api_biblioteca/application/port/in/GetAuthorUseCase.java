package com.example.api_biblioteca.application.port.in;

import com.example.api_biblioteca.application.dto.AuthorDTO;

import java.util.List;

/**
 * Caso de uso para la obtención de autores.
 */
public interface GetAuthorUseCase {
    /**
     * Obtiene un autor por su ID.
     *
     * @param id ID del autor.
     * @return Autor encontrado o null si no existe.
     */
    AuthorDTO getAuthorById(Long id);

    /**
     * Obtiene la lista de todos los autores.
     *
     * @return Lista de autores.
     */
    List<AuthorDTO> getAllAuthors();
}

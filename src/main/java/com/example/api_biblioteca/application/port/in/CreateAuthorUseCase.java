package com.example.api_biblioteca.application.port.in;

import com.example.api_biblioteca.application.dto.AuthorDTO;

/**
 * Caso de uso para la creación de autores.
 */
public interface CreateAuthorUseCase {
    /**
     * Crea un nuevo autor.
     *
     * @param authorDTO Datos del autor a crear.
     * @return Autor creado.
     */
    AuthorDTO createAuthor(AuthorDTO authorDTO);
}

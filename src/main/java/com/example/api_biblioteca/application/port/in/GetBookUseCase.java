package com.example.api_biblioteca.application.port.in;

import com.example.api_biblioteca.application.dto.BookDTO;

import java.util.List;

/**
 * Caso de uso para la obtención de libros.
 */
public interface GetBookUseCase {
    /**
     * Obtiene un libro por su ID.
     *
     * @param id ID del libro.
     * @return Libro encontrado o null si no existe.
     */
    BookDTO getBookById(Long id);

    /**
     * Obtiene la lista de todos los libros.
     *
     * @return Lista de libros.
     */
    List<BookDTO> getAllBooks();
}

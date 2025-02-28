package com.example.api_biblioteca.application.port.in;
import com.example.api_biblioteca.application.dto.BookDTO;

/**
 * Caso de uso para la creación de libros.
 */
public interface CreateBookUseCase {
    /**
     * Crea un nuevo libro.
     *
     * @param bookDTO Datos del libro a crear.
     * @return Libro creado.
     */
    BookDTO createBook(BookDTO bookDTO);
}

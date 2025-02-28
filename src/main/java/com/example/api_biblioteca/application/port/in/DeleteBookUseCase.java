package com.example.api_biblioteca.application.port.in;

/**
 * Caso de uso para eliminar un libro por su ID.
 */
public interface DeleteBookUseCase {
    /**
     * Elimina un libro por su ID.
     *
     * @param id ID del libro a eliminar.
     */
    void deleteBook(Long id);
}
package com.example.api_biblioteca.application.port.out;

import com.example.api_biblioteca.domain.model.Book;

import java.util.List;
import java.util.Optional;

/**
 * Puerto de salida para manejar la persistencia de libros.
 * Define las operaciones necesarias para interactuar con la capa de infraestructura.
 */
public interface BookRepository {

    /**
     * Guarda un nuevo libro o actualiza uno existente.
     *
     * @param book Libro a guardar.
     * @return Libro guardado.
     */
    Book save(Book book);

    /**
     * Busca un libro por su ID.
     *
     * @param id ID del libro.
     * @return Libro si se encuentra, vacío en caso contrario.
     */
    Optional<Book> findById(Long id);

    /**
     * Obtiene todos los libros almacenados.
     *
     * @return Lista de libros.
     */
    List<Book> findAll();

    /**
     * Elimina un libro por su ID.
     *
     * @param id ID del libro a eliminar.
     */
    void deleteById(Long id);
}

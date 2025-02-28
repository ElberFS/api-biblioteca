package com.example.api_biblioteca.application.port.out;

import com.example.api_biblioteca.domain.model.Author;

import java.util.List;
import java.util.Optional;

/**
 * Puerto de salida para manejar la persistencia de autores.
 * Define las operaciones necesarias para interactuar con la capa de infraestructura.
 */
public interface AuthorRepository {

    /**
     * Guarda un nuevo autor o actualiza uno existente.
     *
     * @param author Autor a guardar.
     * @return Autor guardado.
     */
    Author save(Author author);

    /**
     * Busca un autor por su ID.
     *
     * @param id ID del autor.
     * @return Autor si se encuentra, vacío en caso contrario.
     */
    Optional<Author> findById(Long id);

    /**
     * Obtiene todos los autores almacenados.
     *
     * @return Lista de autores.
     */
    List<Author> findAll();

    /**
     * Elimina un autor por su ID.
     *
     * @param id ID del autor a eliminar.
     */
    void deleteById(Long id);
}

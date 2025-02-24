package com.example.api_biblioteca.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Excepción personalizada para indicar que un recurso no ha sido encontrado.
 * Esta excepción devuelve automáticamente un código de estado HTTP 404.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Constructor que acepta un mensaje de error personalizado.
     *
     * @param message Mensaje descriptivo del error.
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructor que acepta un mensaje de error y una causa raíz.
     *
     * @param message Mensaje descriptivo del error.
     * @param cause   La causa raíz del error.
     */
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
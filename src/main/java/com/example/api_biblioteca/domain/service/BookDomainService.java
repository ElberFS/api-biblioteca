package com.example.api_biblioteca.domain.service;

import com.example.api_biblioteca.domain.model.Book;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
/**
 * Servicio de dominio para manejar reglas de negocio relacionadas con los libros.
 */
@Service
public class BookDomainService {

    private static final int MIN_PUBLISHED_YEAR = 1440; // Año aproximado de la invención de la imprenta

    /**
     * Valida si el año de publicación de un libro es lógico.
     *
     * @param book Libro a validar.
     * @return true si el año de publicación es válido, false en caso contrario.
     */
    public boolean isValidPublicationYear(Book book) {
        if (book.getPublishedYear() == null) {
            return false;
        }
        int currentYear = LocalDate.now().getYear();
        return book.getPublishedYear() >= MIN_PUBLISHED_YEAR && book.getPublishedYear() <= currentYear;
    }
}

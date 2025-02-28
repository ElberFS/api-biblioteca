package com.example.api_biblioteca.domain.service;

import com.example.api_biblioteca.domain.model.Author;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

/**
 * Servicio de dominio para manejar reglas de negocio relacionadas con los autores.
 */
@Service
public class AuthorDomainService {

    /**
     * Verifica si un autor es mayor de edad basado en su fecha de nacimiento.
     *
     * @param author Autor a validar.
     * @return true si es mayor de edad, false en caso contrario.
     */
    public boolean isAuthorAdult(Author author) {
        if (author.getBirthdate() == null) {
            return false;
        }
        int age = Period.between(author.getBirthdate(), LocalDate.now()).getYears();
        return age >= 18;
    }
}

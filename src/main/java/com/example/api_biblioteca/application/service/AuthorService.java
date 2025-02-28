package com.example.api_biblioteca.application.service;

import com.example.api_biblioteca.application.dto.AuthorDTO;
import com.example.api_biblioteca.application.port.in.CreateAuthorUseCase;
import com.example.api_biblioteca.application.port.in.GetAuthorUseCase;
import com.example.api_biblioteca.application.port.out.AuthorRepository;
import com.example.api_biblioteca.application.port.in.UpdateAuthorUseCase;
import com.example.api_biblioteca.domain.model.Author;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Servicio de aplicación para gestionar autores.
 * Implementa los casos de uso definidos en los puertos de entrada.
 */
@Service
public class AuthorService implements CreateAuthorUseCase, GetAuthorUseCase , UpdateAuthorUseCase {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    /**
     * Crea un nuevo autor en el sistema.
     *
     * @param authorDTO Datos del autor a crear.
     * @return Autor creado.
     */
    @Override
    public AuthorDTO createAuthor(AuthorDTO authorDTO) {
        Author author = new Author(
                null,  // ID se genera automáticamente
                authorDTO.getName(),
                authorDTO.getBirthdate(),
                authorDTO.getNationality()
        );
        Author savedAuthor = authorRepository.save(author);
        return new AuthorDTO(savedAuthor.getId(), savedAuthor.getName(), savedAuthor.getBirthdate(), savedAuthor.getNationality());
    }

    /**
     * Obtiene un autor por su ID.
     *
     * @param id ID del autor.
     * @return Autor encontrado o null si no existe.
     */
    @Override
    public AuthorDTO getAuthorById(Long id) {
        Optional<Author> author = authorRepository.findById(id);
        return author.map(a -> new AuthorDTO(a.getId(), a.getName(), a.getBirthdate(), a.getNationality())).orElse(null);
    }

    /**
     * Obtiene la lista de todos los autores.
     *
     * @return Lista de autores.
     */
    @Override
    public List<AuthorDTO> getAllAuthors() {
        return authorRepository.findAll().stream()
                .map(a -> new AuthorDTO(a.getId(), a.getName(), a.getBirthdate(), a.getNationality()))
                .collect(Collectors.toList());
    }

    /**
     * Actualizar Autor
     */
    @Override
    public AuthorDTO updateAuthor(Long id, AuthorDTO authorDTO) {
        Optional<Author> authorOptional = authorRepository.findById(id);

        if (authorOptional.isEmpty()) {
            return null;
        }

        Author author = authorOptional.get();
        author.setName(authorDTO.getName());
        author.setNationality(authorDTO.getNationality());
        author.setBirthdate(authorDTO.getBirthdate());
        Author updatedAuthor = authorRepository.save(author);

        return new AuthorDTO(
                updatedAuthor.getId(),
                updatedAuthor.getName(),
                updatedAuthor.getBirthdate(),
                updatedAuthor.getNationality()
        );
    }
}

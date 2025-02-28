package com.example.api_biblioteca.infrastructure.controller;

import com.example.api_biblioteca.application.dto.AuthorDTO;
import com.example.api_biblioteca.application.port.in.CreateAuthorUseCase;
import com.example.api_biblioteca.application.port.in.GetAuthorUseCase;
import com.example.api_biblioteca.application.port.in.UpdateAuthorUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la gestión de autores.
 */
@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final CreateAuthorUseCase createAuthorUseCase;
    private final GetAuthorUseCase getAuthorUseCase;
    private final UpdateAuthorUseCase updateAuthorUseCase;

    public AuthorController(CreateAuthorUseCase createAuthorUseCase, GetAuthorUseCase getAuthorUseCase ,UpdateAuthorUseCase updateAuthorUseCase) {
        this.createAuthorUseCase = createAuthorUseCase;
        this.getAuthorUseCase = getAuthorUseCase;
        this.updateAuthorUseCase = updateAuthorUseCase;
    }

    /**
     * Endpoint para crear un nuevo autor.
     */
    @PostMapping
    public ResponseEntity<AuthorDTO> createAuthor(@RequestBody AuthorDTO authorDTO) {
        AuthorDTO createdAuthor = createAuthorUseCase.createAuthor(authorDTO);
        return ResponseEntity.ok(createdAuthor);
    }

    /**
     * Endpoint para obtener un autor por su ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> getAuthorById(@PathVariable Long id) {
        AuthorDTO author = getAuthorUseCase.getAuthorById(id);
        return author != null ? ResponseEntity.ok(author) : ResponseEntity.notFound().build();
    }

    /**
     * Endpoint para obtener todos los autores.
     */
    @GetMapping
    public ResponseEntity<List<AuthorDTO>> getAllAuthors() {
        List<AuthorDTO> authors = getAuthorUseCase.getAllAuthors();
        return ResponseEntity.ok(authors);
    }

    /**
     * Endpoint para actualizar un autor
     */
    @PutMapping("/{id}")
    public ResponseEntity<AuthorDTO> updateAuthor(@PathVariable Long id, @RequestBody AuthorDTO authorDTO) {
        AuthorDTO updatedAuthor = updateAuthorUseCase.updateAuthor(id, authorDTO);
        return updatedAuthor != null ? ResponseEntity.ok(updatedAuthor) : ResponseEntity.notFound().build();
    }
}

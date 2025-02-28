package com.example.api_biblioteca.application.service;

import com.example.api_biblioteca.application.dto.AuthorDTO;
import com.example.api_biblioteca.application.port.out.AuthorRepository;
import com.example.api_biblioteca.domain.model.Author;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorService authorService;

    private Author author;
    private AuthorDTO authorDTO;

    @BeforeEach
    void setUp() {
        author = new Author(1L, "Gabriel García Márquez", LocalDate.of(1927, 3, 6), "Colombian");
        authorDTO = new AuthorDTO(1L, "Gabriel García Márquez", LocalDate.of(1927, 3, 6), "Colombian");
    }

    @Test
    void shouldCreateAuthorSuccessfully() {
        when(authorRepository.save(any(Author.class))).thenReturn(author);

        AuthorDTO createdAuthor = authorService.createAuthor(authorDTO);

        assertNotNull(createdAuthor);
        assertEquals(authorDTO.getName(), createdAuthor.getName());
        verify(authorRepository, times(1)).save(any(Author.class));
    }

    @Test
    void shouldUpdateAuthorSuccessfully() {
        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));
        when(authorRepository.save(any(Author.class))).thenReturn(author);

        AuthorDTO updatedAuthor = authorService.updateAuthor(1L, authorDTO);

        assertNotNull(updatedAuthor);
        assertEquals(authorDTO.getName(), updatedAuthor.getName());
        verify(authorRepository, times(1)).save(any(Author.class));
    }

    @Test
    void shouldNotUpdateAuthorIfNotExists() {
        when(authorRepository.findById(1L)).thenReturn(Optional.empty());

        AuthorDTO updatedAuthor = authorService.updateAuthor(1L, authorDTO);

        assertNull(updatedAuthor);
    }
}

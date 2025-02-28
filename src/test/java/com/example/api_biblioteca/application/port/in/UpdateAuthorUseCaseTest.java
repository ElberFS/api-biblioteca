package com.example.api_biblioteca.application.port.in;

import com.example.api_biblioteca.application.dto.AuthorDTO;
import com.example.api_biblioteca.application.service.AuthorService;
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
class UpdateAuthorUseCaseTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorService authorService;

    private AuthorDTO authorDTO;
    private Author author;

    @BeforeEach
    void setUp() {
        authorDTO = new AuthorDTO(1L, "Gabriel García Márquez", LocalDate.of(1950, 3, 6), "Colombian");
        author = new Author(1L, "Gabriel García Márquez",LocalDate.of(1930, 3, 6), "Colombian");
    }

    @Test
    void shouldUpdateAuthorSuccessfully() {
        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));
        when(authorRepository.save(any(Author.class))).thenReturn(author);

        AuthorDTO updatedAuthor = authorService.updateAuthor(1L, authorDTO);

        assertNotNull(updatedAuthor);
        assertEquals(author.getId(), updatedAuthor.getId());
        assertEquals(author.getName(), updatedAuthor.getName());
        verify(authorRepository, times(1)).save(any(Author.class));
    }

    @Test
    void shouldNotUpdateIfAuthorDoesNotExist() {
        when(authorRepository.findById(1L)).thenReturn(Optional.empty());

        AuthorDTO updatedAuthor = authorService.updateAuthor(1L, authorDTO);

        assertNull(updatedAuthor);
        verify(authorRepository, never()).save(any(Author.class));
    }
}

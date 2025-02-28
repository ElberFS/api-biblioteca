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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateAuthorUseCaseTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorService authorService;

    private AuthorDTO authorDTO;
    private Author author;

    @BeforeEach
    void setUp() {
        authorDTO = new AuthorDTO(null, "Gabriel García Márquez", LocalDate.of(2000, 11, 6), "Colombian");
        author = new Author(1L, "Gabriel García Márquez",LocalDate.of(1927, 12, 6), "Colombian");
    }

    @Test
    void shouldCreateAuthorSuccessfully() {
        when(authorRepository.save(any(Author.class))).thenReturn(author);

        AuthorDTO createdAuthor = authorService.createAuthor(authorDTO);

        assertNotNull(createdAuthor);
        assertEquals(author.getId(), createdAuthor.getId());
        assertEquals(author.getName(), createdAuthor.getName());
        verify(authorRepository, times(1)).save(any(Author.class));
    }
}

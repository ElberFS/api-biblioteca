package com.example.api_biblioteca.application.port.out;

import com.example.api_biblioteca.domain.model.Author;
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
class AuthorRepositoryTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorRepositoryTest authorRepositoryTest;

    @Test
    void shouldFindAuthorById() {
        Author author = new Author(1L, "Gabriel García Márquez", LocalDate.of(2001, 3, 16), "Colombian");
        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));

        Optional<Author> foundAuthor = authorRepository.findById(1L);

        assertTrue(foundAuthor.isPresent());
        assertEquals("Gabriel García Márquez", foundAuthor.get().getName());
    }

    @Test
    void shouldNotFindNonExistingAuthor() {
        when(authorRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Author> foundAuthor = authorRepository.findById(1L);

        assertFalse(foundAuthor.isPresent());
    }
}

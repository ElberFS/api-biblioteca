package com.example.api_biblioteca.service;

import com.example.api_biblioteca.exception.ResourceNotFoundException;
import com.example.api_biblioteca.model.Author;
import com.example.api_biblioteca.repository.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorService authorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllAuthors() {
        Author author1 = new Author();
        author1.setId(1L);
        author1.setName("Gabriel García Márquez");

        Author author2 = new Author();
        author2.setId(2L);
        author2.setName("Isabel Allende");

        when(authorRepository.findAll()).thenReturn(Arrays.asList(author1, author2));

        List<Author> authors = authorService.getAllAuthors();

        assertNotNull(authors);
        assertEquals(2, authors.size());
        verify(authorRepository, times(1)).findAll();
    }

    @Test
    void testGetAuthorById() {
        Author author = new Author();
        author.setId(1L);
        author.setName("Gabriel García Márquez");

        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));

        Author foundAuthor = authorService.getAuthorById(1L);

        assertNotNull(foundAuthor);
        assertEquals("Gabriel García Márquez", foundAuthor.getName());
        verify(authorRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateAuthor() {
        Author author = new Author();
        author.setName("Jorge Luis Borges");

        when(authorRepository.save(author)).thenReturn(author);

        Author savedAuthor = authorService.createAuthor(author);

        assertNotNull(savedAuthor);
        assertEquals("Jorge Luis Borges", savedAuthor.getName());
        verify(authorRepository, times(1)).save(author);
    }

    @Test
    void testDeleteAuthor() {
        Author author = new Author();
        author.setId(1L);
        author.setName("Gabriel García Márquez");

        doNothing().when(authorRepository).deleteById(1L);

        authorService.deleteAuthor(1L);

        verify(authorRepository, times(1)).deleteById(1L);
    }
}
package com.example.api_biblioteca.controller;

import com.example.api_biblioteca.model.Author;
import com.example.api_biblioteca.service.AuthorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthorControllerTest {

    @Mock
    private AuthorService authorService;

    @InjectMocks
    private AuthorController authorController;

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

        when(authorService.getAllAuthors()).thenReturn(Arrays.asList(author1, author2));

        List<Author> authors = authorController.getAllAuthors();

        assertNotNull(authors);
        assertEquals(2, authors.size());
        verify(authorService, times(1)).getAllAuthors();
    }

    @Test
    void testGetAuthorById() {
        Author author = new Author();
        author.setId(1L);
        author.setName("Gabriel García Márquez");

        when(authorService.getAuthorById(1L)).thenReturn(author);

        ResponseEntity<Author> response = authorController.getAuthorById(1L);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Gabriel García Márquez", response.getBody().getName());
        verify(authorService, times(1)).getAuthorById(1L);
    }
}
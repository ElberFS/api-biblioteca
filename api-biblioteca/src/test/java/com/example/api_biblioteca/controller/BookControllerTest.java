package com.example.api_biblioteca.controller;

import com.example.api_biblioteca.model.Book;
import com.example.api_biblioteca.service.BookService;
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

public class BookControllerTest {
    @Mock
    private BookService bookService;

    @InjectMocks

    private BookController bookController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllBooks() {
        Book book1 = new Book();
        book1.setId(1L);
        book1.setTitle("Cien años de soledad");

        Book book2 = new Book();
        book2.setId(2L);
        book2.setTitle("La casa de los espíritus");

        when(bookService.getAllBooks()).thenReturn(Arrays.asList(book1, book2));

        List<Book> books = bookController.getAllBooks();

        assertNotNull(books);
        assertEquals(2, books.size());
        verify(bookService, times(1)).getAllBooks();
    }

    @Test
    void testGetBookById() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("El coronel no tiene quien le escriba");

        when(bookService.getBookById(1L)).thenReturn(book);

        ResponseEntity<Book> response = bookController.getBookById(1L);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("El coronel no tiene quien le escriba", response.getBody().getTitle());
        verify(bookService, times(1)).getBookById(1L);
    }

    @Test
    void testCreateBook() {
        Book book = new Book();
        book.setTitle("Crónica de una muerte anunciada");

        when(bookService.createBook(book)).thenReturn(book);

        Book createdBook = bookController.createBook(book);

        assertNotNull(createdBook);
        assertEquals("Crónica de una muerte anunciada", createdBook.getTitle());
        verify(bookService, times(1)).createBook(book);
    }

    @Test
    void testDeleteBook() {
        doNothing().when(bookService).deleteBook(1L);

        ResponseEntity<Void> response = bookController.deleteBook(1L);

        assertNotNull(response);
        assertEquals(204, response.getStatusCodeValue());
        verify(bookService, times(1)).deleteBook(1L);
    }
}
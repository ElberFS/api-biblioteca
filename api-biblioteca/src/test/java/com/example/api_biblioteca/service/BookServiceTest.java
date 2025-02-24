package com.example.api_biblioteca.service;

import com.example.api_biblioteca.exception.ResourceNotFoundException;
import com.example.api_biblioteca.model.Book;
import com.example.api_biblioteca.repository.BookRepository;
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

class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

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

        when(bookRepository.findAll()).thenReturn(Arrays.asList(book1, book2));

        List<Book> books = bookService.getAllBooks();

        assertNotNull(books);
        assertEquals(2, books.size());
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void testGetBookById() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("El coronel no tiene quien le escriba");

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        Book foundBook = bookService.getBookById(1L);

        assertNotNull(foundBook);
        assertEquals("El coronel no tiene quien le escriba", foundBook.getTitle());
        verify(bookRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateBook() {
        Book book = new Book();
        book.setTitle("Crónica de una muerte anunciada");

        when(bookRepository.save(book)).thenReturn(book);

        Book savedBook = bookService.createBook(book);

        assertNotNull(savedBook);
        assertEquals("Crónica de una muerte anunciada", savedBook.getTitle());
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void testDeleteBook() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("El amor en los tiempos del cólera");

        doNothing().when(bookRepository).deleteById(1L);

        bookService.deleteBook(1L);

        verify(bookRepository, times(1)).deleteById(1L);
    }
}
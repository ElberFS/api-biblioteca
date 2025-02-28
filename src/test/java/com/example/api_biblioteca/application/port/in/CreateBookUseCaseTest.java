package com.example.api_biblioteca.application.port.in;

import com.example.api_biblioteca.application.dto.BookDTO;
import com.example.api_biblioteca.application.service.BookService;
import com.example.api_biblioteca.application.port.out.BookRepository;
import com.example.api_biblioteca.application.port.out.AuthorRepository;
import com.example.api_biblioteca.domain.model.Author;
import com.example.api_biblioteca.domain.model.Book;
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
class CreateBookUseCaseTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private BookService bookService;

    private BookDTO bookDTO;
    private Book book;
    private Author author;

    @BeforeEach
    void setUp() {
        author = new Author(1L, "Gabriel García Márquez", LocalDate.of(1927, 3, 6), "Colombian");
        bookDTO = new BookDTO(null, "Cien años de soledad", "Novel", 1967, 1L);
        book = new Book(1L, "Cien años de soledad", "Novel", 1967, author);
    }

    @Test
    void shouldCreateBookSuccessfully() {
        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        BookDTO createdBook = bookService.createBook(bookDTO);

        assertNotNull(createdBook);
        assertEquals(book.getId(), createdBook.getId());
        assertEquals(book.getTitle(), createdBook.getTitle());
        verify(bookRepository, times(1)).save(any(Book.class));
    }

    @Test
    void shouldNotCreateBookIfAuthorDoesNotExist() {
        when(authorRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> bookService.createBook(bookDTO));
    }
}

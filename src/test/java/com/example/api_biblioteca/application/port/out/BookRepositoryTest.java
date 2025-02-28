package com.example.api_biblioteca.application.port.out;

import com.example.api_biblioteca.domain.model.Book;
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
class BookRepositoryTest {

    @Mock
    private BookRepository bookRepository;

    @Test
    void shouldFindBookById() {
        Author author = new Author(1L, "Gabriel García Márquez", LocalDate.of(2011, 3, 16), "Colombian");
        Book book = new Book(1L, "Cien años de soledad", "Novel", 1967, author);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        Optional<Book> foundBook = bookRepository.findById(1L);

        assertTrue(foundBook.isPresent());
        assertEquals("Cien años de soledad", foundBook.get().getTitle());
    }

    @Test
    void shouldSaveBookSuccessfully() {
        Author author = new Author(2L, "Isabel Allende", LocalDate.of(2009, 3, 16),"Chilean");
        Book book = new Book(2L, "La casa de los espíritus", "Novel", 1982, author);
        when(bookRepository.save(book)).thenReturn(book);

        Book savedBook = bookRepository.save(book);

        assertNotNull(savedBook);
        assertEquals("La casa de los espíritus", savedBook.getTitle());
    }
}

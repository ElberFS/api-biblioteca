package com.example.api_biblioteca.service;

import com.example.api_biblioteca.exception.ResourceNotFoundException;
import com.example.api_biblioteca.model.Book;
import com.example.api_biblioteca.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.api_biblioteca.repository.AuthorRepository;
import java.util.List;
import com.example.api_biblioteca.model.Author;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository; // Asegúrate de tener un repositorio de Author

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
    }



    public Book createBook( Book book) {
        // Verificar si el autor existe
        if (book.getAuthor() == null || book.getAuthor().getId() == null) {
            throw new IllegalArgumentException("El autor es obligatorio");
        }

        Long authorId = book.getAuthor().getId();
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + authorId));

        book.setAuthor(author); // Asignar el autor encontrado
        return bookRepository.save(book);
    }
    public Book updateBook(Long id, Book bookDetails) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));

        book.setTitle(bookDetails.getTitle());
        book.setGenre(bookDetails.getGenre());
        book.setPublishedYear(bookDetails.getPublishedYear());

        // Verificar si el autor existe antes de asignarlo
        if (bookDetails.getAuthor() != null && bookDetails.getAuthor().getId() != null) {
            Author author = authorRepository.findById(bookDetails.getAuthor().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + bookDetails.getAuthor().getId()));
            book.setAuthor(author);
        }

        return bookRepository.save(book);
    }


    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
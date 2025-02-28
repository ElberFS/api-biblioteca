package com.example.api_biblioteca.application.service;

import com.example.api_biblioteca.application.dto.BookDTO;
import com.example.api_biblioteca.application.port.in.CreateBookUseCase;
import com.example.api_biblioteca.application.port.in.DeleteBookUseCase;
import com.example.api_biblioteca.application.port.in.GetBookUseCase;
import com.example.api_biblioteca.application.port.in.UpdateBookUseCase;
import com.example.api_biblioteca.application.port.out.BookRepository;
import com.example.api_biblioteca.application.port.out.AuthorRepository;
import com.example.api_biblioteca.domain.model.Book;
import com.example.api_biblioteca.domain.model.Author;
import com.example.api_biblioteca.domain.service.BookDomainService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Servicio de aplicación para gestionar libros.
 * Implementa los casos de uso definidos en los puertos de entrada.
 */
@Service
public class BookService implements CreateBookUseCase, GetBookUseCase, UpdateBookUseCase, DeleteBookUseCase {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookDomainService bookDomainService;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository, BookDomainService bookDomainService) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.bookDomainService = bookDomainService;
    }

    /**
     * Crea un nuevo libro en el sistema.
     *
     * @param bookDTO Datos del libro a crear.
     * @return Libro creado.
     */
    @Override
    public BookDTO createBook(BookDTO bookDTO) {
        Optional<Author> authorOptional = authorRepository.findById(bookDTO.getAuthorId());

        if (authorOptional.isEmpty()) {
            throw new IllegalArgumentException("El autor con ID " + bookDTO.getAuthorId() + " no existe.");
        }

        Author author = authorOptional.get();

        // Crear el libro con el objeto Author en lugar de un Long
        Book book = new Book(
                null,  // ID generado automáticamente
                bookDTO.getTitle(),
                bookDTO.getGenre(),
                bookDTO.getPublishedYear(),
                author  // Pasamos el objeto Author, no un Long
        );

        // Validar el año de publicación usando BookDomainService
        if (!bookDomainService.isValidPublicationYear(book)) {
            throw new IllegalArgumentException("El año de publicación no es válido.");
        }

        Book savedBook = bookRepository.save(book);

        return new BookDTO(
                savedBook.getId(),
                savedBook.getTitle(),
                savedBook.getGenre(),
                savedBook.getPublishedYear(),
                savedBook.getAuthor().getId()  // Obtener el ID del autor desde el objeto Author
        );
    }

    /**
     * Obtiene un libro por su ID.
     *
     * @param id ID del libro.
     * @return Libro encontrado o null si no existe.
     */
    @Override
    public BookDTO getBookById(Long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);

        if (bookOptional.isEmpty()) {
            return null; // Retorna null si el libro no existe
        }

        Book book = bookOptional.get();

        return new BookDTO(
                book.getId(),
                book.getTitle(),
                book.getGenre(),
                book.getPublishedYear(),
                book.getAuthor().getId() // Obtener el ID del autor
        );
    }

    /**
     * Obtiene la lista de todos los libros.
     *
     * @return Lista de libros.
     */
    @Override
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(book -> new BookDTO(
                        book.getId(),
                        book.getTitle(),
                        book.getGenre(),
                        book.getPublishedYear(),
                        book.getAuthor().getId() // Obtener el ID del autor
                ))
                .collect(Collectors.toList());
    }

    /**
     * Actualiza un libro existente.
     *
     * @param id      ID del libro a actualizar.
     * @param bookDTO Datos actualizados del libro.
     * @return Libro actualizado o null si no existe.
     */
    @Override
    public BookDTO updateBook(Long id, BookDTO bookDTO) {
        Optional<Book> bookOptional = bookRepository.findById(id);

        if (bookOptional.isEmpty()) {
            return null; // Si no existe el libro, retorna null
        }

        Book book = bookOptional.get();
        book.setTitle(bookDTO.getTitle());
        book.setGenre(bookDTO.getGenre());
        book.setPublishedYear(bookDTO.getPublishedYear());

        // Si se proporciona un nuevo autor, actualizarlo
        if (bookDTO.getAuthorId() != null) {
            Optional<Author> authorOptional = authorRepository.findById(bookDTO.getAuthorId());
            if (authorOptional.isPresent()) {
                book.setAuthor(authorOptional.get());
            }
        }

        Book updatedBook = bookRepository.save(book);

        return new BookDTO(
                updatedBook.getId(),
                updatedBook.getTitle(),
                updatedBook.getGenre(),
                updatedBook.getPublishedYear(),
                updatedBook.getAuthor() != null ? updatedBook.getAuthor().getId() : null
        );
    }

    /**
     * Elimina un libro por su ID.
     *
     * @param id ID del libro a eliminar.
     */
    @Override
    public void deleteBook(Long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);

        if (bookOptional.isEmpty()) {
            throw new IllegalArgumentException("El libro con ID " + id + " no existe.");
        }

        bookRepository.deleteById(id);
    }
}
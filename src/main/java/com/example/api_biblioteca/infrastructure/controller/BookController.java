package com.example.api_biblioteca.infrastructure.controller;

import com.example.api_biblioteca.application.dto.BookDTO;
import com.example.api_biblioteca.application.port.in.CreateBookUseCase;
import com.example.api_biblioteca.application.port.in.DeleteBookUseCase;
import com.example.api_biblioteca.application.port.in.GetBookUseCase;
import com.example.api_biblioteca.application.port.in.UpdateBookUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.List;

/**
 * Controlador REST para la gestión de libros.
 */
@RestController
@RequestMapping("/api/books")
public class BookController {

    private final CreateBookUseCase createBookUseCase;
    private final GetBookUseCase getBookUseCase;
    private final UpdateBookUseCase updateBookUseCase;
    private final DeleteBookUseCase deleteBookUseCase;
    public BookController(CreateBookUseCase createBookUseCase, GetBookUseCase getBookUseCase ,UpdateBookUseCase updateBookUseCase , DeleteBookUseCase deleteBookUseCase) {
        this.createBookUseCase = createBookUseCase;
        this.getBookUseCase = getBookUseCase;
        this.updateBookUseCase = updateBookUseCase;
        this.deleteBookUseCase = deleteBookUseCase;
    }

    /**
     * Endpoint para crear un nuevo libro.
     */
    @PostMapping
    public ResponseEntity<?> createBook(@RequestBody BookDTO bookDTO) {
        try {
            BookDTO createdBook = createBookUseCase.createBook(bookDTO);
            return ResponseEntity.ok(createdBook);
        } catch (Exception e) {
            e.printStackTrace(); // Imprime el error en los logs
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno: " + e.getMessage());
        }
    }

    /**
     * Endpoint para obtener un libro por su ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
        BookDTO book = getBookUseCase.getBookById(id);
        return book != null ? ResponseEntity.ok(book) : ResponseEntity.notFound().build();
    }

    /**
     * Endpoint para obtener todos los libros.
     */
    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        List<BookDTO> books = getBookUseCase.getAllBooks();
        return ResponseEntity.ok(books);
    }

    /**
     * Endpoint para actualizar un libro
     */
    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @RequestBody BookDTO bookDTO) {
        BookDTO updatedBook = updateBookUseCase.updateBook(id, bookDTO);
        return updatedBook != null ? ResponseEntity.ok(updatedBook) : ResponseEntity.notFound().build();
    }

    /**
     * Endpoint para eliminar un libro por su ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        try {
            deleteBookUseCase.deleteBook(id);
            return ResponseEntity.noContent().build(); // Retorna 204 No Content
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build(); // Retorna 404 Not Found si el libro no existe
        }
    }

}

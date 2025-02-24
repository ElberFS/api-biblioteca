package com.example.api_biblioteca.service;

import com.example.api_biblioteca.exception.ResourceNotFoundException;
import com.example.api_biblioteca.model.Author;
import com.example.api_biblioteca.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthorById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + id));
    }

    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
}
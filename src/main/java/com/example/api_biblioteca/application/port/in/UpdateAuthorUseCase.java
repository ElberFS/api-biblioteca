package com.example.api_biblioteca.application.port.in;

import com.example.api_biblioteca.application.dto.AuthorDTO;

public interface UpdateAuthorUseCase {
    AuthorDTO updateAuthor(Long id, AuthorDTO authorDTO);
}

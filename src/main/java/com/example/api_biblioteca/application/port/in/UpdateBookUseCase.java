package com.example.api_biblioteca.application.port.in;

import com.example.api_biblioteca.application.dto.BookDTO;

public interface UpdateBookUseCase {
    BookDTO updateBook(Long id, BookDTO bookDTO);
}

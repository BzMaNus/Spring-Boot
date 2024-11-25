package com.example.demo.services;

import com.example.demo.dtos.BookDetailsDto;
import com.example.demo.dtos.BookResponseDto;
import com.example.demo.entities.Book;
import com.example.demo.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookService {


    private final BookRepository bookRepository;


    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }


    public BookResponseDto<BookDetailsDto> getAllBookDetails(int page, int size) {
        Pageable pageable = PageRequest.of((page - 1), size);
        Page<BookDetailsDto> bookDetailsDtos = bookRepository.findAllBookDetails(pageable);
        BookResponseDto<BookDetailsDto> response = new BookResponseDto<>();
        response.setData(bookDetailsDtos.getContent());
        response.setPage(bookDetailsDtos.getPageable().getPageNumber() + 1);
        response.setOffset(bookDetailsDtos.getPageable().getOffset());
        response.setSize(bookDetailsDtos.getSize());
        response.setTotal(bookDetailsDtos.getTotalElements());
        response.setTotalPage(bookDetailsDtos.getTotalPages());
        return response;

    }

    public Optional<Book> getBookById(UUID bookId) {
        return bookRepository.findById(bookId);
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(UUID bookId, Book bookDetails) {
        return bookRepository.findById(bookId).map(book -> {
            book.setAuthor(bookDetails.getAuthor());
            book.setCategory(bookDetails.getCategory());
            book.setIsbn(bookDetails.getIsbn());
            book.setPrice(bookDetails.getPrice());
            book.setStock(bookDetails.getStock());
            book.setPublishedDate(bookDetails.getPublishedDate());
            book.setDescription(bookDetails.getDescription());
            return bookRepository.save(book);
        }).orElseThrow(() -> new RuntimeException("Book not found with id " + bookId));
    }

    public void deleteBook(UUID bookId) {
        bookRepository.deleteById(bookId);
    }
}

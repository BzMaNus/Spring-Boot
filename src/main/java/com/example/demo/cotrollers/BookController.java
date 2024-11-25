package com.example.demo.cotrollers;

import com.example.demo.dtos.BookDetailsDto;
import com.example.demo.dtos.BookResponseDto;
import com.example.demo.entities.Book;
import com.example.demo.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/details")
    public ResponseEntity<BookResponseDto<BookDetailsDto>> getAllBookDetails(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        BookResponseDto<BookDetailsDto> bookDetails = bookService.getAllBookDetails(page, size);
        return ResponseEntity.ok(bookDetails);
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<Book> getBookById(@PathVariable UUID bookId) {
        return bookService.getBookById(bookId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<Book> updateBook(@PathVariable UUID bookId, @RequestBody Book bookDetails) {
        try {
            return ResponseEntity.ok(bookService.updateBook(bookId, bookDetails));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable UUID bookId) {
        bookService.deleteBook(bookId);
        return ResponseEntity.noContent().build();
    }
}
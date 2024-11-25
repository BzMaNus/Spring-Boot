package com.example.demo.repositories;

import com.example.demo.dtos.BookDetailsDto;
import com.example.demo.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.lang.annotation.Native;
import java.util.List;
import java.util.UUID;

public interface BookRepository extends  JpaRepository<Book,UUID> {
    @Query("""
                SELECT new com.example.demo.dtos.BookDetailsDto(
                    b.bookId,
                    b.isbn,
                    b.price,
                    b.stock,
                    b.publishedDate,
                    b.description,
                    a.name,
                    c.name
                )
                FROM Book b
                JOIN b.author a
                JOIN b.category c
            """)
    Page<BookDetailsDto> findAllBookDetails(Pageable pageable);

}

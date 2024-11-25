package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Table(name = "Books")
public class Book {

    @Id
    @UuidGenerator
    @Column(name = "BookID")
    private UUID bookId;

    @ManyToOne
    @JoinColumn(name = "AuthorID", nullable = false)
    private Author author;

    @ManyToOne
    @JoinColumn(name = "CategoryID", nullable = false)
    private Category category;

    @Column(name = "ISBN", length = 13, nullable = false, unique = true)
    private String isbn;

    @Column(name = "Price", nullable = false)
    private BigDecimal price;

    @Column(name = "Stock", nullable = false)
    private Integer stock = 0;

    @Column(name = "Published_Date")
    private Date publishedDate;

    @Lob
    @Column(name = "Description")
    private String description;


}

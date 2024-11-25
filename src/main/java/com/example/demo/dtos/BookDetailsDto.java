package com.example.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
public class BookDetailsDto {
    private UUID bookId;
    private String isbn;
    private BigDecimal price;
    private Integer stock;
    private Date publishedDate;
    private String description;
    private String authorName;
    private String categoryName;
}

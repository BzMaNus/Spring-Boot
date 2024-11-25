package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Data
@Table(name = "Categories")
public class Category {

    @Id
    @UuidGenerator
    @Column(name = "CategoryID")
    private UUID categoryId;

    @Column(name = "Name", length = 100, nullable = false, unique = true)
    private String name;

    @Lob
    @Column(name = "Description")
    private String description;
}

package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Table(name = "Authors")
public class Author {
    @Id
    @UuidGenerator
    @Column(name = "AuthorID")
    private UUID authorId;

    @Column(name = "Name", nullable = false)
    private String name;

    @Lob
    @Column(name = "Bio")
    private String bio;

    @Column(name = "DateOfBirth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Column(name = "Nationality", length = 100)
    private String nationality;
}

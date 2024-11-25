package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Table(name = "Customers")
public class Customer {

    @Id
    @UuidGenerator
    @Column(name = "CustomerID")
    private UUID customerId;

    @Column(name = "FullName", nullable = false)
    private String fullName;

    @Column(name = "Email", nullable = false, unique = true)
    private String email;

    @Column(name = "PhoneNumber", length = 15)
    private String phoneNumber;

    @Lob
    @Column(name = "Address")
    private String address;

    @Column(name = "CreatedAt", nullable = false, updatable = false)
    private Date createdAt = new Date();
}

package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;


import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Table(name = "Orders")
public class Order {

    @Id
    @UuidGenerator
    @Column(name = "OrderID")
    private UUID orderId;

    @ManyToOne
    @JoinColumn(name = "CustomerID", nullable = false)
    private Customer customer;

    @Column(name = "OrderDate", nullable = false, updatable = false)
    private Date orderDate = new Date();

    @Column(name = "TotalAmount", nullable = false)
    private BigDecimal totalAmount;

    @Column(name = "Status", length = 20, nullable = false)
    private String status = "Pending";
}

package com.example.tenderapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class SalesOrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(nullable = false)
    @JsonBackReference
    private SalesOrder salesOrder;

    private Integer quantity;

    private BigDecimal unitPrice;

    private BigDecimal lineTotal;
}
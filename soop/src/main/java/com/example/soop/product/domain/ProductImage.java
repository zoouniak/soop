package com.example.soop.product.domain;

import jakarta.persistence.*;
import lombok.Getter;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
public class ProductImage {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(updatable = false, unique = true)
    private String name;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
}

package com.example.soop.product.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

import static jakarta.persistence.CascadeType.REMOVE;

@Entity
@Getter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String summary;
    private String description;
    private int price;

    @OneToMany(mappedBy = "product", cascade = REMOVE, orphanRemoval = true)
    private List<ProductImage> images;
}

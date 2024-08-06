package com.example.soop.product.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

import static jakarta.persistence.CascadeType.REMOVE;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String summary;

    @Column(nullable = false)

    private String description;

    @Column(nullable = false)
    private int price;

    @OneToMany(mappedBy = "product", cascade = REMOVE, orphanRemoval = true)
    private List<ProductImage> images;

    @Builder
    public Product(String name, String summary, String description, int price, List<ProductImage> images) {
        this.name = name;
        this.summary = summary;
        this.description = description;
        this.price = price;
        this.images = images;
    }
}

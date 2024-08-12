package com.example.soop.product.dto.response;

import com.example.soop.product.domain.Product;
import com.example.soop.product.domain.ProductImage;

import java.util.List;
import java.util.stream.Collectors;

public record ProductDetailResponse(
        Long id,
        String name,
        String summary,
        String description,
        int price,
        List<String> imageNames
) {
    public static ProductDetailResponse of(Product product) {
        List<String> images = product.getImages().stream().map(ProductImage::getName)
                .collect(Collectors.toList());
        return new ProductDetailResponse(
                product.getId(),
                product.getName(),
                product.getSummary(),
                product.getDescription(),
                product.getPrice(),
                images
        );
    }
}

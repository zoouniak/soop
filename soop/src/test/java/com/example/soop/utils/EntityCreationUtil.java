package com.example.soop.utils;

import com.example.soop.product.domain.Product;

public final class EntityCreationUtil {
    public static Product product(){
        return Product.builder()
                .name("스냅사진")
                .summary("요약")
                .description("스냅 사진 상품 설명")
                .price(10000)
                .build();
    }
}

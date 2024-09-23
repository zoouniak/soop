package com.example.soop.product.dto.response;

import java.util.List;

public record ProductPageResponse(
        int totalPage,
        List<ProductResponse> productList
) {
}

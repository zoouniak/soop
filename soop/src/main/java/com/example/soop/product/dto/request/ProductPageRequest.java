package com.example.soop.product.dto.request;

import jakarta.validation.constraints.Min;

public record ProductPageRequest(
        @Min(value = 1, message = "페이지 번호는 1 이상이어야 합니다.")
        int page
) {
}

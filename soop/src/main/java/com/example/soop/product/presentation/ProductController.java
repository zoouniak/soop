package com.example.soop.product.presentation;

import com.example.soop.product.dto.response.ProductDetailResponse;
import com.example.soop.product.dto.response.ProductPageResponse;
import com.example.soop.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    // 상품 조회(페이지 당 4개)
    @GetMapping("/products")
    public ResponseEntity<ProductPageResponse> getProducts(@RequestParam(defaultValue = "1") final int page) {
        return ResponseEntity.ok(productService.getProducts(page));
    }

    // 상품 상세 조회
    @GetMapping("/product/{id}")
    public ResponseEntity<ProductDetailResponse> getProductDetail(@PathVariable(name = "id") final Long productId) {
        return ResponseEntity.ok(productService.getProductDetail(productId));
    }

}

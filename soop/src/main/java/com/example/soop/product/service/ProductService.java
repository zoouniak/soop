package com.example.soop.product.service;

import com.example.soop.global.exception.ProductException;
import com.example.soop.product.domain.Product;
import com.example.soop.product.domain.ProductRepository;
import com.example.soop.product.dto.response.ProductDetailResponse;
import com.example.soop.product.dto.response.ProductPageResponse;
import com.example.soop.product.dto.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.soop.global.exception.ExceptionCode.NO_SUCH_PRODUCT;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public ProductPageResponse getProducts(int page) {
        Page<Product> pages = productRepository.findAll(PageRequest.of(page - 1, 4, Sort.by(Sort.Direction.DESC, "id")));
        int totalPages = pages.getTotalPages();

        List<ProductResponse> products = pages.getContent().stream()
                .map(product -> new ProductResponse(
                        product.getId(),
                        product.getName(),
                        product.getPrice()
                ))
                .collect(Collectors.toList());
        return new ProductPageResponse(totalPages, products);
    }

    public ProductDetailResponse getProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductException(NO_SUCH_PRODUCT));

        return ProductDetailResponse.of(product);
    }
}

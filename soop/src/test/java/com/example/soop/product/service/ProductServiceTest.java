package com.example.soop.product.service;

import com.example.soop.global.exception.ProductException;
import com.example.soop.product.domain.Product;
import com.example.soop.product.domain.ProductRepository;
import com.example.soop.product.dto.response.ProductDetailResponse;
import com.example.soop.product.dto.response.ProductPageResponse;
import com.example.soop.utils.EntityCreationUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Test
    void 상품목록을_조회한다() {
        // given
        Product coupleSnap = EntityCreationUtil.product();
        Product babySnap = EntityCreationUtil.product();
        List<Product> products = List.of(coupleSnap, babySnap);

        Page<Product> pages = new PageImpl<>(products, PageRequest.of(0, 4, Sort.by(Sort.Direction.DESC, "id")), 2);

        given(productRepository.findAll(any(Pageable.class)))
                .willReturn(pages);

        // when
        ProductPageResponse response = productService.getProducts(1);

        // then
        Assertions.assertEquals(2, response.productList().size());
    }

    @Test
    void 존재하는_상품상세를_조회한다() {
        Product product = EntityCreationUtil.product();
        given(productRepository.findById(any())).willReturn(Optional.of(product));

        // when
        ProductDetailResponse productDetail = productService.getProductDetail(1L);

        // then
        Assertions.assertEquals(product.getPrice(), productDetail.price());
    }

    @Test
    void 존재하지않는_상품상세를_조회하면_예외가_발생한다() {
        given(productRepository.findById(any())).willReturn(Optional.empty());

        // when & then
        assertThatThrownBy(() -> productService.getProductDetail(1L))
                .isInstanceOf(ProductException.class)
                .extracting("code")
                .isEqualTo(301);
    }
}
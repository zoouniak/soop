package com.example.soop.utils;

import com.example.soop.product.domain.Product;
import com.example.soop.timeslot.domain.TimeSlot;

import java.time.LocalDate;
import java.time.LocalTime;

public final class EntityCreationUtil {
    public static Product product() {
        return Product.builder()
                .name("스냅사진")
                .summary("요약")
                .description("스냅 사진 상품 설명")
                .price("10000")
                .build();
    }

    public static TimeSlot timeSlot(Long id) {
        return new TimeSlot(id,
                LocalDate.of(2024,9,22),
                LocalTime.of(11,0),
                true);
    }
}

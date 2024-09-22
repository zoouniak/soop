package com.example.soop.reservation.dto.req;

public record ReserveRequest(
        Long productId,
        Long timeSlotId
) {
}

package com.example.soop.reservation.dto.res;

import com.example.soop.reservation.status.ReservationStatus;

import java.time.LocalDateTime;

public record ReservationResponse(
        Long reservationId,
        String productName,
        LocalDateTime createdAt,
        ReservationStatus status

) {
}

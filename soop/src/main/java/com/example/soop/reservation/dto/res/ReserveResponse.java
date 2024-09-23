package com.example.soop.reservation.dto.res;

import java.time.LocalDate;
import java.time.LocalTime;

public record ReserveResponse(
        Long reservationId,
        String productName,
        LocalDate date,
        LocalTime startedAt,
        String userName,
        String userPhone

) {
}

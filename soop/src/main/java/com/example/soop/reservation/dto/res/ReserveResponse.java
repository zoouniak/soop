package com.example.soop.reservation.dto.res;

import com.example.soop.reservation.domain.Reservation;
import com.example.soop.reservation.status.ReservationStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record ReserveResponse(
        Long reservationId,
        LocalDateTime createdAt,
        String productName,
        LocalDate date,
        LocalTime startedAt,
        String userName,
        String userPhone,
        ReservationStatus status

) {
    public static ReserveResponse of(Reservation reservation) {
        return new ReserveResponse(
                reservation.getId(),
                reservation.getCreatedAt(),
                reservation.getProduct().getName(),
                reservation.getTimeSlot().getDate(),
                reservation.getTimeSlot().getStartedAt(),
                reservation.getUser().getNickname(),
                reservation.getUser().getPhone(),
                reservation.getStatus()
        );
    }
}

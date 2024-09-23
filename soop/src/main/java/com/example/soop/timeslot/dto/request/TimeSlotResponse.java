package com.example.soop.timeslot.dto.request;

import java.time.LocalDate;
import java.time.LocalTime;

public record TimeSlotResponse(
        Long id,
        LocalDate date,
        LocalTime startedAt,
        boolean isAvailable
) {
}

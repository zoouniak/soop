package com.example.soop.timeslot.dto.request;

import java.time.LocalTime;

public record TimeSlotResponse(
        Long id,
        LocalTime startedAt,
        boolean isAvailable
) {
}

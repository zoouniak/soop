package com.example.soop.timeslot.service;

import com.example.soop.timeslot.domain.TimeSlot;
import com.example.soop.timeslot.domain.repository.TimeSlotRepository;
import com.example.soop.timeslot.dto.request.TimeSlotResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TimeSlotService {
    public final TimeSlotRepository timeSlotRepository;

    public List<TimeSlotResponse> getTimeSlotByDate(LocalDate date) {
        List<TimeSlot> allByDate = timeSlotRepository.findAllByDate(date);
        return allByDate.stream().map((slot)
                        -> new TimeSlotResponse(slot.getId(), slot.getStartedAt(), slot.isAvailable()))
                .collect(Collectors.toList());
    }
}

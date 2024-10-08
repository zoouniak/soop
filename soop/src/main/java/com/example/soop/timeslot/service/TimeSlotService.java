package com.example.soop.timeslot.service;

import com.example.soop.global.exception.TimeSlotException;
import com.example.soop.timeslot.domain.TimeSlot;
import com.example.soop.timeslot.domain.repository.TimeSlotRepository;
import com.example.soop.timeslot.dto.request.TimeSlotResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.soop.global.exception.ExceptionCode.NO_SUCH_TIMESLOT;

@Service
@RequiredArgsConstructor
public class TimeSlotService {
    public final TimeSlotRepository timeSlotRepository;

    public List<TimeSlotResponse> getTimeSlotByDate(final LocalDate date) {
        List<TimeSlot> allByDate = timeSlotRepository.findAllByDateAndIsAvailableTrue(date);
        return allByDate.stream().map((slot)
                        -> new TimeSlotResponse(slot.getId(), slot.getDate(), slot.getStartedAt(), slot.isAvailable()))
                .collect(Collectors.toList());
    }

    public TimeSlotResponse getTimeSlotInfo(final Long timeSlotId) {
        TimeSlot slot = findTimeSlot(timeSlotId);
        return new TimeSlotResponse(slot.getId(),
                slot.getDate(), slot.getStartedAt(), slot.isAvailable());
    }

    private TimeSlot findTimeSlot(Long timeSlotId) {
        return timeSlotRepository.findById(timeSlotId).orElseThrow(() -> new TimeSlotException(NO_SUCH_TIMESLOT));
    }
}

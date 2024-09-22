package com.example.soop.timeslot.service;

import com.example.soop.timeslot.domain.TimeSlot;
import com.example.soop.timeslot.domain.repository.TimeSlotRepository;
import com.example.soop.timeslot.dto.request.TimeSlotResponse;
import com.example.soop.utils.EntityCreationUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TimeSlotServiceTest {
    @InjectMocks
    private TimeSlotService timeSlotService;
    @Mock
    private TimeSlotRepository timeSlotRepository;

    @Test
    public void 특정날짜에_가능한_타임슬롯을_조회한다() {
        // given
        LocalDate date = LocalDate.of(2024, 9, 17);  // Example date
        List<TimeSlot> mockTimeSlots = Arrays.asList(
                new TimeSlot(date, LocalTime.of(10, 0), true),
                new TimeSlot(date, LocalTime.of(14, 0), true)
        );


        given(timeSlotRepository.findAllByDateAndIsAvailableTrue(date)).willReturn(mockTimeSlots);

        // when
        List<TimeSlotResponse> result = timeSlotService.getAvailableTimeSlotByDate(date);

        // then
        assertEquals(2, result.size());  // Verify the size of the result list
        assertEquals(LocalTime.of(10, 0), result.get(0).startedAt());
        assertTrue(result.get(0).isAvailable());

        assertEquals(LocalTime.of(14, 0), result.get(1).startedAt());
        assertTrue(result.get(1).isAvailable());

        verify(timeSlotRepository, times(1)).findAllByDateAndIsAvailableTrue(date);
    }

    @Test
    public void 타임슬롯을_조회한다() {
        TimeSlot timeSlot = EntityCreationUtil.timeSlot(1L);
        given(timeSlotRepository.findById(1L)).willReturn(Optional.of(timeSlot));

        // when
        TimeSlotResponse result = timeSlotService.getTimeSlotInfo(1L);

        // then
        assertEquals(1L, result.id());
        assertEquals(LocalDate.of(2024, 9, 22), result.date());
        assertEquals(LocalTime.of(11, 0), result.startedAt());
        assertTrue(result.isAvailable());

        verify(timeSlotRepository,times(1)).findById(1L);
    }
}
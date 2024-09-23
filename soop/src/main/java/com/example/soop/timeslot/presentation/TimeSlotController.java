package com.example.soop.timeslot.presentation;

import com.example.soop.timeslot.dto.request.TimeSlotResponse;
import com.example.soop.timeslot.service.TimeSlotService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TimeSlotController {
    private final TimeSlotService timeSlotService;
    @GetMapping("/timeslot")
    public ResponseEntity<List<TimeSlotResponse>> getTimeSlotByDate(@RequestParam final LocalDate date){
        return ResponseEntity.ok(timeSlotService.getTimeSlotByDate(date));
    }

    @GetMapping("/timeslot/{id}")
    public ResponseEntity<TimeSlotResponse> getTimeSlotInfo(@PathVariable(name = "id") final Long timeslotId){
        return ResponseEntity.ok(timeSlotService.getTimeSlotInfo(timeslotId));
    }



}

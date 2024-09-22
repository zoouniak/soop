package com.example.soop.timeslot.domain.repository;

import com.example.soop.timeslot.domain.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TimeSlotRepository extends JpaRepository<TimeSlot,Long> {
    List<TimeSlot> findAllByDateAndIsAvailableTrue(LocalDate date);
}

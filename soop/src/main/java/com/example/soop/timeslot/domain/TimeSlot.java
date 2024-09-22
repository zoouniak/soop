package com.example.soop.timeslot.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TimeSlot {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private LocalTime startedAt;
    private LocalTime endedAt;
    private boolean isAvailable;


    public TimeSlot(Long id, LocalDate date, LocalTime startedAt, boolean isAvailable) {
        this.id = id;
        this.date = date;
        this.startedAt = startedAt;
        this.isAvailable = isAvailable;
    }

    @Builder
    public TimeSlot(LocalDate date, LocalTime startedAt,  boolean isAvailable) {
        this.date = date;
        this.startedAt = startedAt;
        this.isAvailable = isAvailable;
    }

    public void setUnAvailable(){
        this.isAvailable=false;
    }
}

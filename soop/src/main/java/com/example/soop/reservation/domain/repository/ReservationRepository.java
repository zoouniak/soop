package com.example.soop.reservation.domain.repository;

import com.example.soop.reservation.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}

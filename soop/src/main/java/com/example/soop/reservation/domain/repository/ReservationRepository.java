package com.example.soop.reservation.domain.repository;

import com.example.soop.reservation.domain.Reservation;
import com.example.soop.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findAllByUser(User user);
    boolean existsByUserIdAndId(Long userId, Long id);
}

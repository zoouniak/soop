package com.example.soop.reservation.domain;

import com.example.soop.product.domain.Product;
import com.example.soop.reservation.status.ReservationStatus;
import com.example.soop.timeslot.domain.TimeSlot;
import com.example.soop.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reservation {
    @Id
    private long id;

    @OneToOne
    @JoinColumn(name = "time_slot_id", unique = true)
    private TimeSlot timeSlot;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;
    @Column
    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    @Column
    private LocalDateTime createdAt;

    @Builder
    public Reservation(Long id,TimeSlot timeSlot, User user, Product product,LocalDateTime createdAt) {
        this.id=id;
        this.timeSlot = timeSlot;
        this.user = user;
        this.product = product;
        this.status = ReservationStatus.RESERVED;
        this.createdAt = createdAt;
    }
}

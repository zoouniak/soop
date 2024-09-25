package com.example.soop.reservation.presentation;

import com.example.soop.auth.domain.Accessor;
import com.example.soop.auth.domain.Auth;
import com.example.soop.reservation.dto.req.ReservationCancelRequest;
import com.example.soop.reservation.dto.req.ReserveRequest;
import com.example.soop.reservation.dto.res.ReservationResponse;
import com.example.soop.reservation.dto.res.ReserveResponse;
import com.example.soop.reservation.service.ReserveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservation")
public class ReserveController {
    private final ReserveService reserveService;

    @PostMapping("/reserve")
    public ResponseEntity<ReserveResponse> reserve(@Auth Accessor accessor, @RequestBody ReserveRequest reserveRequest) {
        return ResponseEntity.ok(reserveService.reserve(accessor.getMemberId(), reserveRequest));
    }

    @PostMapping("/cancel")
    public ResponseEntity<?> cancel(@Auth Accessor accessor,  @RequestBody ReservationCancelRequest request) {
        reserveService.cancel(accessor.getMemberId(), request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/complete")
    public ResponseEntity<?> complete(@Auth Accessor accessor, @RequestParam(name = "id") Long id) {
        reserveService.complete(accessor.getMemberId(), id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/my")
    public ResponseEntity<List<ReservationResponse>> getReservations(@Auth Accessor accessor) {
        return ResponseEntity.ok(reserveService.getReservations(accessor.getMemberId()));
    }

    @GetMapping("/{reservationId}")
    public ResponseEntity<ReserveResponse> getReservation(@Auth Accessor accessor, @PathVariable(name = "reservationId") Long id) {
        return ResponseEntity.ok(reserveService.getReservation(accessor.getMemberId(), id));
    }
}

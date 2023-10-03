package com.WHotels.HotelMIS.controller;


import com.WHotels.HotelMIS.model.Reservation;
import com.WHotels.HotelMIS.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/isReserved/{table_id}")
    public ResponseEntity<Boolean> getIsReserved(@PathVariable("table_id") Integer tableId) {
        boolean isReserved = reservationService.isReserved(tableId);
        return ResponseEntity.ok(isReserved);
    }

    @GetMapping("/future")
    public ResponseEntity<List<Reservation>> getFutureReservation() {
        List<Reservation> reservations= reservationService.getFutureReservations();
        return ResponseEntity.ok(reservations);
    }

    @DeleteMapping("/{reservationId}")
    public void deleteReservationById(@PathVariable Long reservationId) {
        System.out.println(reservationId);
        reservationService.deleteReservationById(reservationId);
    }


}

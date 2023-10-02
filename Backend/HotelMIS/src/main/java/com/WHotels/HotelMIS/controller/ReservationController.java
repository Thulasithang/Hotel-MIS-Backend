package com.WHotels.HotelMIS.controller;


import com.WHotels.HotelMIS.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

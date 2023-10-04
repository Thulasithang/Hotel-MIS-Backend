package com.WHotels.HotelMIS.controller;


import com.WHotels.HotelMIS.dto.*;
import com.WHotels.HotelMIS.service.BookingManagementService;
import com.WHotels.HotelMIS.service.RoomOrderManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/room/booking")
@CrossOrigin
public class RoomOrderManagementController {

    @Autowired
    RoomOrderManagementService roomOrderManagementService;

    @Autowired
    BookingManagementService bookingManagementService;

    @GetMapping("/search-availability")
    ResponseEntity<List<RoomAvailabilitySearchResponse>> checkAvailability(@RequestParam String checkIn, @RequestParam String checkOut, @RequestParam int adultCount, @RequestParam int childrenCount, @RequestParam(required = false) String promo) throws Exception{
        List<RoomAvailabilitySearchResponse> response = roomOrderManagementService.checkAvailability(checkIn, checkOut, adultCount, childrenCount);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping()
    ResponseEntity<BookingResponse> doBooking(@RequestBody BookingRequest bookingRequest) throws Exception{
        BookingResponse response = bookingManagementService.doBooking(bookingRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/delete-selections")
    ResponseEntity<String> deleteSelection(@RequestBody DeleteSelectionRequest deleteSelectionRequest) throws Exception{
        String response = bookingManagementService.deleteSelection(deleteSelectionRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/confirm-booking")
    ResponseEntity<ConfirmedResponse> confirmBooking(@RequestBody ConfirmedRequest confirmedRequest) throws Exception{
        ConfirmedResponse response = bookingManagementService.confirmBooking(confirmedRequest);
        return ResponseEntity.ok(response);
    }

}

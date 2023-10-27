package com.WHotels.HotelMIS.controller;


import com.WHotels.HotelMIS.dto.SearchByFiltersResponse;

import com.WHotels.HotelMIS.dto.resort.AppHomeResponse;
import com.WHotels.HotelMIS.model.Room;
import com.WHotels.HotelMIS.model.RoomType;
import com.WHotels.HotelMIS.service.AppService;
import com.WHotels.HotelMIS.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1/app")
@CrossOrigin
public class AppController {

    @Autowired
    RoomTypeService roomTypeService;

    @Autowired
    AppService appService;

    @GetMapping("/home")
    ResponseEntity<AppHomeResponse> getHomeScreen() throws Exception{
        AppHomeResponse response = appService.getHomeScreen();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/booking")
    ResponseEntity<List<SearchByFiltersResponse>> getBookingDetailsByFilters(@RequestParam(required = false) Long bookingId, @RequestParam(required = false)String customerName) throws Exception{
        List<SearchByFiltersResponse> response = appService.getBookingDetailsByFilters(bookingId, customerName);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/room")
    ResponseEntity<List<Room>> getRooms(@RequestParam(required = false)Long roomId, @RequestParam(required = false)String roomStatus) throws Exception{
        List<Room> response = appService.getRooms(roomId, roomStatus);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/roomType/create")
    public ResponseEntity<String> addRoomType(@RequestBody RoomType roomType) throws IOException {
        return roomTypeService.addRoomType(roomType);
    }

    // create booking - /room/booking - POST



}

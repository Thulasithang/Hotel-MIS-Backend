package com.WHotels.HotelMIS.controller;

import com.WHotels.HotelMIS.model.RoomType;
import com.WHotels.HotelMIS.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1/roomType")
@CrossOrigin
public class RoomTypeController {

    @Autowired
    RoomTypeService roomTypeService;

    @GetMapping("get")
    public ResponseEntity<List<RoomType>> getRoomTypes(){
        return roomTypeService.getRoomTypes();
    }





}

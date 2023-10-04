package com.WHotels.HotelMIS.service;


import com.WHotels.HotelMIS.model.RoomType;
import com.WHotels.HotelMIS.repository.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoomTypeService {

    @Autowired
    RoomTypeRepository roomTypeRepository;

    private final String FOLDER_PATH= "C:/Users/hp/Desktop/New folder/";

    public ResponseEntity<List<RoomType>> getRoomTypes() {
        try {
            return new ResponseEntity<>(roomTypeRepository.findAll(), HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addRoomType(RoomType roomType ) throws IOException {
        roomTypeRepository.save(roomType);;
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }
}

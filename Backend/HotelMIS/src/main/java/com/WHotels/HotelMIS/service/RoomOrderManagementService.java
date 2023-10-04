package com.WHotels.HotelMIS.service;


import com.WHotels.HotelMIS.dto.RoomAvailabilitySearchResponse;
import com.WHotels.HotelMIS.model.Room;
import com.WHotels.HotelMIS.model.RoomType;
import com.WHotels.HotelMIS.repository.BookingRepository;
import com.WHotels.HotelMIS.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Service
public class RoomOrderManagementService {
    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    RoomRepository roomRepository;
    public List<RoomAvailabilitySearchResponse> checkAvailability(String checkIn, String checkOut, int adultCount, int childrenCount) throws Exception{
        try{
            Map<RoomType,Integer> roomTypeIntegerMap = new HashMap<>();
            List<Long> availableRoomIdList = bookingRepository.searchAvailability(checkIn, checkOut, childrenCount, adultCount);
            List<Room> availableRoomList = roomRepository.findByIds(availableRoomIdList);
            for (Room room: availableRoomList){
                if(roomTypeIntegerMap.containsKey(room.getRoomType())){
                    Integer count = roomTypeIntegerMap.get(room.getRoomType());
                    roomTypeIntegerMap.put(room.getRoomType(), count+1);
                }else {
                    roomTypeIntegerMap.put(room.getRoomType(), 1);
                }
            }

            List<RoomAvailabilitySearchResponse> responseList = new ArrayList<>();
            for (Map.Entry<RoomType, Integer> entry : roomTypeIntegerMap.entrySet()) {
                RoomAvailabilitySearchResponse roomAvailabilitySearchResponse = responseMapping(entry.getKey(), entry.getValue());
                responseList.add(roomAvailabilitySearchResponse);
            }

            return responseList;


        }catch (Exception ex){
            //ignored
            throw new Exception("Internal Server Error");
        }

    }

    private RoomAvailabilitySearchResponse responseMapping(RoomType key, Integer value) throws Exception {
        try{
            RoomAvailabilitySearchResponse roomAvailabilitySearchResponse = new RoomAvailabilitySearchResponse();
            roomAvailabilitySearchResponse.setRoomType(key.getType());
            roomAvailabilitySearchResponse.setPrice(Long.valueOf(key.getRoomPrice()));
            roomAvailabilitySearchResponse.setDescription(key.getDescription());
            roomAvailabilitySearchResponse.setMaxNoOfAdults(key.getMaxAdultOccupancy());
            roomAvailabilitySearchResponse.setMaxNoOfChildren(key.getMaxChildOccupancy());
            roomAvailabilitySearchResponse.setCount(value);
            return roomAvailabilitySearchResponse;
        }catch (Exception ex){
            throw new Exception("Internal Server Error");
        }
    }
}

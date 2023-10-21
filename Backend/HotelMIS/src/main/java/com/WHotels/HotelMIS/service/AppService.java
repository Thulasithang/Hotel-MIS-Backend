package com.WHotels.HotelMIS.service;


import com.WHotels.HotelMIS.dto.resort.AppHomeResponse;
import com.WHotels.HotelMIS.dto.SearchByFiltersResponse;
import com.WHotels.HotelMIS.model.Booking;
import com.WHotels.HotelMIS.model.Room;
import com.WHotels.HotelMIS.model.RoomType;
import com.WHotels.HotelMIS.repository.BookingRepository;
import com.WHotels.HotelMIS.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class AppService {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    RoomRepository roomRepository;
    public AppHomeResponse getHomeScreen() throws Exception {
        try{
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedDate = currentDate.format(formatter);
            Long checkIn = bookingRepository.getCheckInCount(formattedDate);
            Long checkOut = bookingRepository.getCheckOutCount(formattedDate);
            Long totalNoOfGuests = bookingRepository.findTotalNoOfGuests(formattedDate);
            List<Room> availableRoomList = roomRepository.findAvailableRooms(formattedDate);

            AppHomeResponse appHomeResponse = new AppHomeResponse();
            appHomeResponse.setTodayCheckIn(checkIn);
            appHomeResponse.setTodayCheckOut(checkOut);
            appHomeResponse.setTodayInHotel(totalNoOfGuests);
            appHomeResponse.setTotalAvailableRoom(availableRoomList.size());
            return appHomeResponse;
        }catch (Exception ex){
            throw new Exception("Exception in Service Layer!");
        }
    }

    public List<SearchByFiltersResponse> getBookingDetailsByFilters(Long bookingId, String customerName)throws Exception {
        try{
            List<Booking> bookingList = bookingRepository.getBookingDetailsByFilters(bookingId, customerName);
            List<SearchByFiltersResponse> searchByFiltersResponseList = new ArrayList<>();
            for (Booking booking : bookingList){
                SearchByFiltersResponse searchByFiltersResponse = new SearchByFiltersResponse();
                searchByFiltersResponse.setBookingId(booking.getBookingId());
                searchByFiltersResponse.setCheckIn(String.valueOf(booking.getCheckIn()));
                searchByFiltersResponse.setCheckOut(String.valueOf(booking.getCheckOut()));
                searchByFiltersResponse.setFirstName(booking.getCustomer().getFirstName());
                searchByFiltersResponse.setLastName(booking.getCustomer().getLastName());
                searchByFiltersResponse.setBookingStatus(booking.getBookingStatus());
                searchByFiltersResponse.setRoomId(String.valueOf(booking.getRoom().getRoomId()));
                searchByFiltersResponseList.add(searchByFiltersResponse);

            }
            return searchByFiltersResponseList;
        }catch (Exception ex){
            throw new Exception("Exception in Service Layer!");
        }
    }

    public List<Room> getRooms(Long roomId, String roomStatus) throws Exception{
        try{
            List<Room> roomList = roomRepository.getRoomById(roomId, roomStatus);
            return roomList;
        }
        catch (Exception ex){
            throw new Exception("Exception in Service Layer!");
        }
    }
}

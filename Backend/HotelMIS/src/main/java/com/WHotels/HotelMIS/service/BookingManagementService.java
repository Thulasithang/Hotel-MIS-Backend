package com.WHotels.HotelMIS.service;

import com.WHotels.HotelMIS.dto.*;
import com.WHotels.HotelMIS.model.Customer;
import com.WHotels.HotelMIS.model.Booking;
import com.WHotels.HotelMIS.model.Room;
import com.WHotels.HotelMIS.repository.CustomerRepository;
import com.WHotels.HotelMIS.repository.BookingRepository;
import com.WHotels.HotelMIS.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingManagementService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    BookingRepository bookingRepository;

    public BookingResponse doBooking(BookingRequest bookingRequest) throws Exception{
        try{
            List<Long> roomIdList = roomRepository.selectARoom(bookingRequest.getCheckIn(), bookingRequest.getCheckOut(), bookingRequest.getRoomType());
            if(roomIdList==null || roomIdList.isEmpty()) throw new Exception("No Rooms Available for this Room Type!");
            Optional<Room> roomOptional = roomRepository.findById(roomIdList.get(0));
            Booking booking = bookingMapping(roomOptional.get(), bookingRequest);
            Booking savedBooking  = bookingRepository.save(booking);

            return BookingResponse.builder().bookingStatus("In Progress").bookingId(savedBooking.getBookingId()).build();


        }catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    private Booking bookingMapping(Room room, BookingRequest bookingRequest) throws Exception{
        try{
            Booking booking = new Booking();
            booking.setBookingStatus("In Progress");
            booking.setCheckIn(Date.valueOf(bookingRequest.getCheckIn()));
            booking.setCheckOut(Date.valueOf(bookingRequest.getCheckOut()));
            booking.setTotal(bookingRequest.getPrice());
            booking.setCustomer(null);
            booking.setRoom(room);
            return booking;
        }catch (Exception ex){
            throw new Exception("Exception in Service Layer");
        }
    }

    public String deleteSelection(DeleteSelectionRequest deleteSelectionRequest) throws Exception{
        try{
            bookingRepository.deleteAllByIdInBatch(deleteSelectionRequest.getBookingIdList());
            return "Successfully Deleted";
        }catch (Exception ex){
            throw new Exception("Try Again");
        }
    }
    public ConfirmedResponse confirmBooking(ConfirmedRequest confirmedRequest)throws Exception {
        try{
            Optional<Customer> customerOptional = customerRepository.findById(confirmedRequest.getCustomerId());
            if(customerOptional.isEmpty())throw new Exception("Customer Not Found");
            List<Booking> bookingList = bookingRepository.findAllById(confirmedRequest.getBookingIdList());
            for(Booking booking : bookingList){
                booking.setCustomer(customerOptional.get());
                booking.setBookingStatus("Confirmed");
                bookingRepository.save(booking);
            }
            return ConfirmedResponse.builder().customerId(customerOptional.get().getCustomerId()).build();
        }catch (Exception ex){
            throw new Exception("Try Again");
        }
    }

}

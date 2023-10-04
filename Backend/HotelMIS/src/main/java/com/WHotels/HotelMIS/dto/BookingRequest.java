package com.WHotels.HotelMIS.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BookingRequest {
    private String checkIn;
    private String checkOut;
    private String roomType;
    private Long price;
}

package com.WHotels.HotelMIS.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class SearchByFiltersResponse {
    private  Long bookingId;
    private String checkIn;
    private String checkOut;
    private String bookingStatus;
    private String firstName;
    private String lastName;
    private String roomId;
}

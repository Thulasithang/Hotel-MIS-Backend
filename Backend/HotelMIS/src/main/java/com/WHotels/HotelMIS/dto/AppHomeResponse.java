package com.WHotels.HotelMIS.dto.resort;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AppHomeResponse {
    private Long todayCheckIn;
    private Long todayCheckOut;
    private Long todayInHotel;
    private Integer totalAvailableRoom;
}

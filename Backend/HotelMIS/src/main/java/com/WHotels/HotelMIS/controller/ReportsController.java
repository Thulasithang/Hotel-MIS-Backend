package com.WHotels.HotelMIS.controller;

//import com.WHotels.HotelMIS.dto.MonthlyRevenue;
import com.WHotels.HotelMIS.service.ReportsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.sql.Date;

@RestController
@RequestMapping(path = "api/v1/report")
public class ReportsController {

    public ReportsController(ReportsService reportsService) {
        this.reportsService = reportsService;
    }

    private final ReportsService reportsService;

    @GetMapping("/checkins/{date}")
    public long getCheckInsByDate(@PathVariable Date date) {
        return reportsService.getCheckInsByDate(date);
    }

    @GetMapping("/checkouts/{date}")
    public long getCheckOutsByDate(@PathVariable Date date) {
        return reportsService.getCheckOutsByDate(date);
    }

    @GetMapping("/occupied-guests/{date}")
    public long getOccupiedGuests(@PathVariable Date date) {
        return reportsService.getOccupiedGuests(date);
    }

//    @GetMapping("/revenue-monthly/{date}")
//    public BigDecimal getRevenueByMonth(Date date){
//        return reportsService.getRevenueByMonth(date);
//    }

//    @GetMapping("/{year}")
//    public MonthlyRevenue getMonthlyRevenue(@PathVariable int year) {
//        return reportsService.getMonthlyRevenue(year);
//    }

}

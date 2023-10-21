package com.WHotels.HotelMIS.controller;

import com.WHotels.HotelMIS.dto.MonthlyRevenue;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.WHotels.HotelMIS.service.RevenueService;
@RestController
@RequestMapping(path="api/v1/rev")
public class RevenueController {

    private final RevenueService revenueService;


    public RevenueController(RevenueService revenueService) {
        this.revenueService = revenueService;
    }

    @GetMapping
    public ResponseEntity<MonthlyRevenue>  getMonthlyRevenue() {
        return revenueService.getMonthlyRevenue();
    }
}

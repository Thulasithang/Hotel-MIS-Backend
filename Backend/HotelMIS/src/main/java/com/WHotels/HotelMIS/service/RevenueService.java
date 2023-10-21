package com.WHotels.HotelMIS.service;

import com.WHotels.HotelMIS.dto.MonthlyRevenue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Year;
import java.util.List;
import java.util.Map;

@Service
public class RevenueService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ResponseEntity<MonthlyRevenue> getMonthlyRevenue() {
        Year currentYear = Year.now();
        int yearValue = currentYear.getValue();
        String sql = "SELECT * FROM get_yearly_revenue(" + yearValue + ")";

        //String sql = "SELECT * FROM get_yearly_revenue(2023)";
        // System.out.println("sql: " + sql);

        MonthlyRevenue monthlyRevenue = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(MonthlyRevenue.class));

        // Print the MonthlyRevenue object
        System.out.println("MonthlyRevenue: " + monthlyRevenue.getSeptember());

        return new ResponseEntity<>(monthlyRevenue, HttpStatus.OK);
    }
}

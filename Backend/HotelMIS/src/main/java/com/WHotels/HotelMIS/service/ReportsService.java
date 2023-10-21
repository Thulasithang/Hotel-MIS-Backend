package com.WHotels.HotelMIS.service;

//import com.WHotels.HotelMIS.dto.MonthlyRevenue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;

@Service
public class ReportsService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public long getCheckInsByDate(Date date) {
        // get the number of check ins for today by querying the databse
        String sql = "SELECT get_check_ins_today(?)";

        return jdbcTemplate.queryForObject(sql, Long.class, date);
    }

    public long getCheckOutsByDate(Date date) {
        // get the number of check ots for today by querying the database
        String sql = "SELECT get_check_outs_today(?)";

        return jdbcTemplate.queryForObject(sql, Long.class, date);
    }

    public long getOccupiedGuests(Date date) {
        // get the number of guests in the hotel by querying the database
        String sql = "SELECT get_occupied_guests(?)";

        return jdbcTemplate.queryForObject(sql, Long.class, date);
    }

//    public BigDecimal getRevenueByMonth(Date date) {
//        // get the number of guests in the hotel by querying the database
//        String sql = "select get_monthly_revenue('2023-10-02')";
//
//        System.out.println(jdbcTemplate.queryForObject(sql, BigDecimal.class, date).toString());
//
//        return jdbcTemplate.queryForObject(sql, BigDecimal.class, date);
//    }







//    public MonthlyRevenue getMonthlyRevenue(int year) {
//        String sql = "    SELECT\n" +
//                "        get_monthly_revenue(DATE(2023 || '-01-01')) AS january,\n" +
//                "        get_monthly_revenue(DATE(2023 || '-02-01')) AS february,\n" +
//                "        get_monthly_revenue(DATE(2023 || '-03-01')) AS march,\n" +
//                "        get_monthly_revenue(DATE(2023 || '-04-01')) AS april,\n" +
//                "        get_monthly_revenue(DATE(2023 || '-05-01')) AS may,\n" +
//                "        get_monthly_revenue(DATE(2023 || '-06-01')) AS june,\n" +
//                "        get_monthly_revenue(DATE(2023 || '-07-01')) AS july,\n" +
//                "        get_monthly_revenue(DATE(2023 || '-08-01')) AS august,\n" +
//                "        get_monthly_revenue(DATE(2023 || '-09-01')) AS september,\n" +
//                "        get_monthly_revenue(DATE(2023 || '-10-01')) AS october,\n" +
//                "        get_monthly_revenue(DATE(2023 || '-11-01')) AS november,\n" +
//                "        get_monthly_revenue(DATE(2023 || '-12-01')) AS december;";
//
//        return jdbcTemplate.query(sql, rs -> {
//            MonthlyRevenue monthlyRevenue = new MonthlyRevenue();
//            monthlyRevenue.setJanuary(rs.getBigDecimal("january"));
//            monthlyRevenue.setFebruary(rs.getBigDecimal("february"));
//            monthlyRevenue.setMarch(rs.getBigDecimal("march"));
//            monthlyRevenue.setApril(rs.getBigDecimal("april"));
//            monthlyRevenue.setMay(rs.getBigDecimal("may"));
//            monthlyRevenue.setJune(rs.getBigDecimal("june"));
//            monthlyRevenue.setJuly(rs.getBigDecimal("july"));
//            monthlyRevenue.setAugust(rs.getBigDecimal("august"));
//            monthlyRevenue.setSeptember(rs.getBigDecimal("september"));
//            monthlyRevenue.setOctober(rs.getBigDecimal("october"));
//            monthlyRevenue.setNovember(rs.getBigDecimal("november"));
//            monthlyRevenue.setDecember(rs.getBigDecimal("december"));
//            return monthlyRevenue;
//        });
//    }


}

package com.WHotels.HotelMIS.repository;

import com.WHotels.HotelMIS.model.Reservation;
import com.WHotels.HotelMIS.model.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    // Custom query to check for reservations for a specific table, date, and time
    @Query("SELECT r FROM Reservation r " +
            "WHERE r.tableId = :tableId " +
            "AND r.date = :date " +
            "AND :currentTime BETWEEN r.startTime AND r.endTime")
    Reservation findReservationsByTableIdAndDateAndTime(
            @Param("tableId") Integer tableId,
            @Param("date") Date date,
            @Param("currentTime") Time currentTime
    );

    @Query("SELECT r FROM Reservation r WHERE r.date >= :date")
    List<Reservation> findReservationsForTodayAndFuture(@Param("date") Date date);



}

package com.WHotels.HotelMIS.service;

import com.WHotels.HotelMIS.model.Reservation;
import com.WHotels.HotelMIS.repository.ReservationRepository;
import com.WHotels.HotelMIS.service.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;
    @InjectMocks
    private ReservationService reservationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        // reservationService = new ReservationService(reservationRepository);
    }

    @Test
    void testIsReservedWhenTableIsReserved() {
        int tableId = 1;
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        Date currentSqlDate = Date.valueOf(currentDate);
        Time currentSqlTime = Time.valueOf(currentTime);

        // Create a mock Reservation object
        Reservation reservation = new Reservation();
        reservation.setReservationId(1);
        reservation.setTableId(tableId);
        reservation.setDate(currentSqlDate);
        reservation.setStartTime(Time.valueOf(currentTime.minusHours(1))); //reservation starts 1 hour before current time
        reservation.setEndTime(Time.valueOf(currentTime.plusHours(1))); // reservation ends 1 hour current time

        // Mock the behavior of the reservationRepository
        when(reservationRepository.findReservationsByTableIdAndDateAndTime(tableId, currentSqlDate, currentSqlTime))
                .thenReturn(reservation);

        // Test if the table is reserved
        boolean isReserved = reservationService.isReserved(tableId);

        assertTrue(isReserved);

        // Verify that the reservationRepository's method was called once with the expected arguments
        verify(reservationRepository, times(1))
                .findReservationsByTableIdAndDateAndTime(tableId, currentSqlDate, currentSqlTime);
    }

    @Test
    void testIsReservedWhenTableIsNotReserved() {
        int tableId = 2;
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        Date currentSqlDate = Date.valueOf(currentDate);
        Time currentSqlTime = Time.valueOf(currentTime);

        // Mock the behavior of the reservationRepository to return null (no reservation found)
        when(reservationRepository.findReservationsByTableIdAndDateAndTime(tableId, currentSqlDate, currentSqlTime))
                .thenReturn(null);

        // Test if the table is not reserved
        boolean isReserved = reservationService.isReserved(tableId);

        assertFalse(isReserved);

        // Verify that the reservationRepository's method was called with the expected arguments
        verify(reservationRepository, times(1))
                .findReservationsByTableIdAndDateAndTime(tableId, currentSqlDate, currentSqlTime);
    }

    @Test
    void testGetFutureReservations() {
        LocalDate currentDate = LocalDate.now();
        Date currentSqlDate = Date.valueOf(currentDate);

        // Create a list of mock reservations
        List<Reservation> reservations = new ArrayList<>();
        int j=0;
        for (int i = 1; i <= 3; i++) {
            Reservation reservation = new Reservation();
            reservation.setReservationId(i);
            reservation.setDate(currentSqlDate);
            reservation.setStartTime(Time.valueOf(LocalTime.now().plusHours(i)));
            reservation.setEndTime(Time.valueOf(LocalTime.now().plusHours(i + 1)));
            reservations.add(reservation);
        }

        // Mock the behavior of the reservationRepository to return the list of reservations
        when(reservationRepository.findReservationsForTodayAndFuture(currentSqlDate))
                .thenReturn(reservations);

        // Test getting future reservations
        List<Reservation> futureReservations = reservationService.getFutureReservations();

        assertEquals(3, futureReservations.size());

        // Verify that the reservationRepository's method was called
        verify(reservationRepository, times(1)).findReservationsForTodayAndFuture(currentSqlDate);
    }


}
package com.WHotels.HotelMIS.service;

import com.WHotels.HotelMIS.model.Reservation;
import com.WHotels.HotelMIS.model.Table;
import com.WHotels.HotelMIS.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public boolean isReserved(int table_id){

        LocalDate currentDate = LocalDate.now();
        java.sql.Date currentSqlDate = java.sql.Date.valueOf(currentDate);

        LocalTime currentTime = LocalTime.now();
        java.sql.Time currentSqlTime = java.sql.Time.valueOf(currentTime);

        Optional<Reservation> optionalReservation = Optional.ofNullable(reservationRepository.findReservationsByTableIdAndDateAndTime(table_id,currentSqlDate ,currentSqlTime));
        boolean isReserved=optionalReservation.isPresent();

        return isReserved;
    }

    public List<Reservation> getFutureReservations(){
        LocalDate currentDate = LocalDate.now();
        java.sql.Date currentSqlDate = java.sql.Date.valueOf(currentDate);
        return reservationRepository.findReservationsForTodayAndFuture(currentSqlDate);
    }

    public void deleteReservationById(Long reservationId) {
        reservationRepository.deleteById(reservationId);
    }
}

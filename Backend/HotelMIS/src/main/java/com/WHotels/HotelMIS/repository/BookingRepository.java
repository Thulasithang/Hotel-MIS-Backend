package com.WHotels.HotelMIS.repository;

import com.WHotels.HotelMIS.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query(nativeQuery = true, value = "select\n" +
            "\tr.room_id \n" +
            "from\n" +
            "\troom r\n" +
            "where\n" +
            "\tr.room_id not in (\n" +
            "\tselect\n" +
            "\t\tb.room_id\n" +
            "\tfrom\n" +
            "\t\tbooking b\n" +
            "\twhere\n" +
            "\t\tb.check_in >= (cast (cast(:checkIn as text) as date))\n" +
            "\t\t\tand b.check_out <=(cast (cast(:checkOut as text) as date)))\n" +
            "\tand r.room_type_id in (\n" +
            "\tselect\n" +
            "\t\trt.room_type_id\n" +
            "\tfrom\n" +
            "\t\troom_type rt\n" +
            "\twhere\n" +
            "\t\trt.max_adult_occupancy >=:adultCount\n" +
            "\t\tand rt.max_child_occupancy >=:childrenCount)")
    List<Long> searchAvailability(String checkIn, String checkOut, int childrenCount, int adultCount);


    @Query(nativeQuery = true, value = "select sum(b.total) from booking b where customer_id =:customerId and booking_status = 'Confirmed'")
    Long findTotalSum(Integer customerId);

    @Query(nativeQuery = true, value = "select count(*) from booking b where b.check_in = (cast (cast(:checkIn as text) as date)) and b.booking_status='Confirmed'")
    Long getCheckInCount(String checkIn);

    @Query(nativeQuery = true, value = "select count(*) from booking b where b.check_out = (cast (cast(:checkOut as text) as date)) and b.booking_status ='Confirmed'")
    Long getCheckOutCount(String checkOut);


    @Query(nativeQuery = true, value = "select\n" +
            "\tsum(b.no_of_guests)\n" +
            "from\n" +
            "\tbooking b\n" +
            "where\n" +
            "\tb.check_in <= (cast (cast(:today as text) as date))\n" +
            "\tand \n" +
            "b.check_out >= (cast (cast(:today as text) as date))\n" +
            "\tand b.booking_status = 'Confirmed'")
    Long findTotalNoOfGuests(String today);

    @Query(nativeQuery = true, value = "SELECT *\n" +
            "FROM booking b\n" +
            "WHERE (:bookingId IS NULL OR b.booking_id = :bookingId)\n" +
            "  AND (\n" +
            "    :customerName IS NULL\n" +
            "    OR b.customer_id IN (\n" +
            "      SELECT c.customer_id\n" +
            "      FROM customer c\n" +
            "      WHERE (\n" +
            "        c.first_name ILIKE '%' || :customerName || '%' \n" +
            "        OR c.last_name ILIKE '%' || :customerName || '%'\n" +
            "      )\n" +
            "    )\n" +
            "  )")
    List<Booking> getBookingDetailsByFilters(Long bookingId, String customerName);

    @Modifying
    @Query(nativeQuery = true, value = "ALTER TABLE booking ALTER COLUMN booking_id RESTART WITH 1")
    void resetAutoIncrement();

}

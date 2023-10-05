package com.WHotels.HotelMIS.repository;

import com.WHotels.HotelMIS.model.Booking;
import com.WHotels.HotelMIS.model.Customer;
import com.WHotels.HotelMIS.model.Room;
import com.WHotels.HotelMIS.model.RoomType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.awt.print.Book;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
class BookingRepositoryTest {
    @Autowired
    private BookingRepository underTest;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomTypeRepository roomTypeRepository;

    @Autowired
    private CustomerRepository customerRepository;


    @BeforeEach
    void setUp() {
        roomRepository.resetAutoIncrement();
        underTest.resetAutoIncrement();
    }

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
        roomRepository.deleteAll();
        roomTypeRepository.deleteAll();

    }

    @Test
    void checkSearchAvailability() {
        RoomType roomType1 = new RoomType();
        roomType1.setType("Standard");
        roomType1.setMaxAdultOccupancy(2);
        roomType1.setMaxChildOccupancy(1);
        roomType1.setRoomSize(300);
        roomType1.setDescription("A standard room with a view.");
        roomType1.setRoomPrice(100L);

        RoomType roomType2 = new RoomType();
        roomType2.setType("Deluxe");
        roomType2.setMaxAdultOccupancy(2);
        roomType2.setMaxChildOccupancy(0);
        roomType2.setRoomSize(200);
        roomType2.setDescription("A deluxe room with a view.");
        roomType2.setRoomPrice(80L);

        roomTypeRepository.save(roomType1);
        roomTypeRepository.save(roomType2);

        Room room1 = new Room(1L, "booked", roomType1);
        Room room2 = new Room(2L, "booked", roomType1);
        Room room3 = new Room(3L, "booked", roomType2);
        roomRepository.save(room1);
        roomRepository.save(room2);
        roomRepository.save(room3);


        Booking booking1 = new Booking();
        booking1.setCheckIn(Date.valueOf("2023-10-02"));
        booking1.setCheckOut(Date.valueOf("2023-10-03"));
        booking1.setTotal(500L);
        booking1.setBookingStatus("Confirmed");
        booking1.setRoom(room1);
        underTest.save(booking1);

        Booking booking2 = new Booking();
        booking2.setCheckIn(Date.valueOf("2023-10-04"));
        booking2.setCheckOut(Date.valueOf("2023-10-05"));
        booking2.setTotal(500L);
        booking2.setBookingStatus("Confirmed");
        booking2.setRoom(room2);
        underTest.save(booking2);

        String checkIn = "2023-10-04";
        String checkOut = "2023-10-05";
        int childrenCount = 1;
        int adultCount = 2;

        List<Long> availableRooms = underTest.searchAvailability(checkIn, checkOut, childrenCount, adultCount);

        List<Long> expectedAvailableRooms = Arrays.asList(room1.getRoomId());

        assertEquals(expectedAvailableRooms, availableRooms);

    }


    @Test

    void checkGetCheckInCount() {

        RoomType roomType1 = new RoomType();
        roomType1.setType("Standard");
        roomType1.setMaxAdultOccupancy(2);
        roomType1.setMaxChildOccupancy(1);
        roomType1.setRoomSize(300);
        roomType1.setDescription("A standard room with a view.");
        roomType1.setRoomPrice(100L);
        roomTypeRepository.save(roomType1);

        Room room1 = new Room(1L, "booked", roomType1);
        Room room2 = new Room(2L, "booked", roomType1);
        Room room3 = new Room(3L, "booked", roomType1);
        roomRepository.save(room1);
        roomRepository.save(room2);
        roomRepository.save(room3);


        Booking booking1 = new Booking();
        booking1.setCheckIn(Date.valueOf("2023-10-02"));
        booking1.setCheckOut(Date.valueOf("2023-10-03"));
        booking1.setTotal(500L);
        booking1.setBookingStatus("Confirmed");
        booking1.setRoom(room1);
        underTest.save(booking1);

        Booking booking2 = new Booking();
        booking2.setCheckIn(Date.valueOf("2023-10-02"));
        booking2.setCheckOut(Date.valueOf("2023-10-03"));
        booking2.setTotal(500L);
        booking2.setBookingStatus("Confirmed");
        booking2.setRoom(room2);
        underTest.save(booking2);

        Booking booking3 = new Booking();
        booking3.setCheckIn(Date.valueOf("2023-10-02"));
        booking3.setCheckOut(Date.valueOf("2023-10-03"));
        booking3.setTotal(500L);
        booking3.setBookingStatus("InProgress");
        booking3.setRoom(room3);
        underTest.save(booking3);

        String checkIn = "2023-10-02";

        Long checkInCount = underTest.getCheckInCount(checkIn);

        assertEquals(2, checkInCount);
    }

    @Test

    void checkGetCheckOutCount() {

        RoomType roomType1 = new RoomType();
        roomType1.setType("Standard");
        roomType1.setMaxAdultOccupancy(2);
        roomType1.setMaxChildOccupancy(1);
        roomType1.setRoomSize(300);
        roomType1.setDescription("A standard room with a view.");
        roomType1.setRoomPrice(100L);
        roomTypeRepository.save(roomType1);

        Room room1 = new Room(1L, "booked", roomType1);
        Room room2 = new Room(2L, "booked", roomType1);
        Room room3 = new Room(3L, "booked", roomType1);
        roomRepository.save(room1);
        roomRepository.save(room2);
        roomRepository.save(room3);


        Booking booking1 = new Booking();
        booking1.setCheckIn(Date.valueOf("2023-10-02"));
        booking1.setCheckOut(Date.valueOf("2023-10-03"));
        booking1.setTotal(500L);
        booking1.setBookingStatus("Confirmed");
        booking1.setRoom(room1);
        underTest.save(booking1);

        Booking booking2 = new Booking();
        booking2.setCheckIn(Date.valueOf("2023-10-02"));
        booking2.setCheckOut(Date.valueOf("2023-10-03"));
        booking2.setTotal(500L);
        booking2.setBookingStatus("Confirmed");
        booking2.setRoom(room2);
        underTest.save(booking2);

        Booking booking3 = new Booking();
        booking3.setCheckIn(Date.valueOf("2023-10-02"));
        booking3.setCheckOut(Date.valueOf("2023-10-03"));
        booking3.setTotal(500L);
        booking3.setBookingStatus("InProgress");
        booking3.setRoom(room3);
        underTest.save(booking3);

        String checkOut = "2023-10-03";

        Long checkInCount = underTest.getCheckOutCount(checkOut);

        assertEquals(2, checkInCount);
    }


    @Test
    void getBookingDetailsByFilters() {


        RoomType roomType1 = new RoomType();
        roomType1.setType("Standard");
        roomType1.setMaxAdultOccupancy(2);
        roomType1.setMaxChildOccupancy(1);
        roomType1.setRoomSize(300);
        roomType1.setDescription("A standard room with a view.");
        roomType1.setRoomPrice(100L);
        roomTypeRepository.save(roomType1);

        Room room1 = new Room(1L, "booked", roomType1);
        roomRepository.save(room1);

        Customer customer1 = new Customer();
        customer1.setCustomerId(1);
        customer1.setNicNumber("123456789");
        customer1.setFirstName("John");
        customer1.setLastName("Doe");
        customerRepository.save(customer1);

        Customer customer2 = new Customer();
        customer2.setCustomerId(2);
        customer2.setNicNumber("987654321");
        customer2.setFirstName("Jane");
        customer2.setLastName("Doe");
        customerRepository.save(customer2);

        Booking booking1 = new Booking();
        booking1.setCheckIn(Date.valueOf("2023-10-01"));
        booking1.setCheckOut(Date.valueOf("2023-10-02"));
        booking1.setTotal(500L);
        booking1.setBookingStatus("Confirmed");
        booking1.setRoom(room1);
        booking1.setCustomer(customer1);
        underTest.save(booking1);

        Booking booking2 = new Booking();
        booking2.setCheckIn(Date.valueOf("2023-10-02"));
        booking2.setCheckOut(Date.valueOf("2023-10-03"));
        booking2.setTotal(500L);
        booking2.setBookingStatus("Confirmed");
        booking2.setRoom(room1);
        booking2.setCustomer(customer1);
        underTest.save(booking2);

        Booking booking3 = new Booking();
        booking3.setCheckIn(Date.valueOf("2023-10-03"));
        booking3.setCheckOut(Date.valueOf("2023-10-04"));
        booking3.setTotal(500L);
        booking3.setBookingStatus("InProgress");
        booking3.setRoom(room1);
        booking3.setCustomer(customer2);
        underTest.save(booking3);

        Long bookingId = 1L;
        String customerName1 = "John";
        String customerName2 = "Doe";

        // test1
        List<Booking> bookingList1 = underTest.getBookingDetailsByFilters(bookingId, customerName1);
        List<Long> bookingIds1 = bookingList1.stream().map(Booking::getBookingId).toList();
        List<Long> bookingIds1Expected = Arrays.asList(booking1.getBookingId());

        //test2
        List<Booking> bookingList2 = underTest.getBookingDetailsByFilters(null, null);
        List<Long> bookingIds2 = bookingList2.stream().map(Booking::getBookingId).toList();
        List<Long> bookingIds2Expected = Arrays.asList(booking1.getBookingId(), booking2.getBookingId(), booking3.getBookingId());

        //test3
        List<Booking> bookingList3 = underTest.getBookingDetailsByFilters(null, customerName2);
        List<Long> bookingIds3 = bookingList3.stream().map(Booking :: getBookingId).toList();
        List<Long> bookingIds3Expected = Arrays.asList(booking1.getBookingId(), booking2.getBookingId(), booking3.getBookingId());

        assertEquals(bookingIds1Expected, bookingIds1);
        assertEquals(bookingIds2Expected, bookingIds2);
        assertEquals(bookingIds3Expected, bookingIds3);


    }
}
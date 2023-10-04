package com.WHotels.HotelMIS.repository;

import com.WHotels.HotelMIS.model.Booking;
import com.WHotels.HotelMIS.model.Room;
import com.WHotels.HotelMIS.model.RoomType;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
class RoomRepositoryTest {

    @Autowired
    private RoomRepository underTest;

    @Autowired
    private RoomTypeRepository roomTypeRepository; // Inject the RoomTypeRepository

    @Autowired
    private BookingRepository bookingRepository;

    @BeforeEach
    void setUp(){
        underTest.resetAutoIncrement();
        underTest.deleteAll();
        roomTypeRepository.deleteAll();
        bookingRepository.deleteAll();
        }

    @Test
    void checkFindByIds() {

//      given
        RoomType roomType = new RoomType();
        roomType.setType("Standard");
        roomType.setMaxAdultOccupancy(2);
        roomType.setMaxChildOccupancy(1);
        roomType.setRoomSize(300);
        roomType.setDescription("A standard room with a view.");
        roomType.setRoomPrice(100L);
        roomType = roomTypeRepository.save(roomType);

        Room room1 = new Room(1L, "booked", roomType);
        Room room2 = new Room(2L, "booked", roomType);
        underTest.save(room1);
        underTest.save(room2);

        List<Long> roomIdsToFind = Arrays.asList(room1.getRoomId(),room2.getRoomId());

//        when
        List<Room> foundRoomList = underTest.findByIds(roomIdsToFind);

//      then
        assertEquals(2,foundRoomList.size());
    }

    @Test
    void checkSelectARoom() {

//        given
            // Create and save a RoomType entity
            RoomType roomType1 = new RoomType();
            roomType1.setType("Standard");
            roomType1.setMaxAdultOccupancy(2);
            roomType1.setMaxChildOccupancy(1);
            roomType1.setRoomSize(300);
            roomType1.setDescription("A standard room with a view.");
            roomType1.setRoomPrice(100L);
            roomTypeRepository.save(roomType1);

            List<RoomType> roomTypes = roomTypeRepository.findAll();

            // Create and save a Room entity
            Room room1 = new Room(1L, "available", roomType1);
            Room room2 = new Room(2L, "available", roomType1);
            underTest.save(room1);
            underTest.save(room2);

            Booking booking = new Booking();
            booking.setCheckIn(Date.valueOf("2023-10-02")); // Replace with the actual check-in date
            booking.setCheckOut(Date.valueOf("2023-10-03")); // Replace with the actual check-out date
            booking.setTotal(500L); // Replace with the actual total amount
            booking.setBookingStatus("Confirmed"); // Set the booking status, e.g., "Confirmed"
            booking.setRoom(room1);
            bookingRepository.save(booking);



            // Define check-in and check-out dates and room type
            String checkIn = "2023-10-01";
            String checkOut = "2023-10-03";
            String roomType = "Standard";


            // Call the selectARoom query method
            List<Long> selectedRoomIds = underTest.selectARoom(checkIn, checkOut, roomType);

            // Check that the list contains the room ID of the available room
            List<Long> expectedRoomIds = Arrays.asList(room2.getRoomId());


        System.out.println(underTest.findAll());
        System.out.println(expectedRoomIds);


        assertEquals(expectedRoomIds,selectedRoomIds);
        }


    @Test
    void canFindAvailableRooms() {
        // Create and save a RoomType entity
        RoomType roomType1 = new RoomType();
        roomType1.setType("Standard");
        roomType1.setMaxAdultOccupancy(2);
        roomType1.setMaxChildOccupancy(1);
        roomType1.setRoomSize(300);
        roomType1.setDescription("A standard room with a view.");
        roomType1.setRoomPrice(100L);
        roomTypeRepository.save(roomType1);

        List<RoomType> roomTypes = roomTypeRepository.findAll();

        // Create and save a Room entity
        Room room1 = new Room(1L, "available", roomType1);
        Room room2 = new Room(2L, "available", roomType1);
        underTest.save(room1);
        underTest.save(room2);


        Booking booking = new Booking();
        booking.setCheckIn(Date.valueOf("2023-10-01")); // Replace with the actual check-in date
        booking.setCheckOut(Date.valueOf("2023-10-03")); // Replace with the actual check-out date
        booking.setTotal(500L); // Replace with the actual total amount
        booking.setBookingStatus("Confirmed"); // Set the booking status, e.g., "Confirmed"
        booking.setRoom(room1);
        bookingRepository.save(booking);




        String today = "2023-10-02";

        List<Room> availableRoomList = underTest.findAvailableRooms(today);
        List<Long> availableRoomIds = availableRoomList.stream()
                .map(Room::getRoomId)
                .collect(Collectors.toList());


        List<Long> expectedAvailableRoomIds  = Arrays.asList(room2.getRoomId());


        assertEquals(expectedAvailableRoomIds,availableRoomIds);
    }


    @Test
    void checkGetRoomById() {
        // Arrange: Create a test Room and save it to the database

        RoomType roomType1 = new RoomType();
        roomType1.setType("Standard");
        roomType1.setMaxAdultOccupancy(2);
        roomType1.setMaxChildOccupancy(1);
        roomType1.setRoomSize(300);
        roomType1.setDescription("A standard room with a view.");
        roomType1.setRoomPrice(100L);
        roomTypeRepository.save(roomType1);



        Room room = new Room(1L, "available", roomType1); // Replace 1L with your test data
        underTest.save(room);


        // Act: Call the getRoomById method
        List<Room> foundRooms = underTest.getRoomById(room.getRoomId());

        // Assert: Check that the result contains the expected room
        assertNotNull(foundRooms);
        assertEquals(1, foundRooms.size());
        assertEquals(room.getRoomId(), foundRooms.get(0).getRoomId());
    }
}
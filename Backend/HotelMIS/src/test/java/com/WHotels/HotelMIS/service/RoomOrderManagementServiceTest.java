package com.WHotels.HotelMIS.service;

import com.WHotels.HotelMIS.dto.RoomAvailabilitySearchResponse;
import com.WHotels.HotelMIS.model.Room;
import com.WHotels.HotelMIS.model.RoomType;
import com.WHotels.HotelMIS.repository.BookingRepository;
import com.WHotels.HotelMIS.repository.RoomRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class RoomOrderManagementServiceTest {

    @Mock
    RoomRepository roomRepository;

    @Mock
    BookingRepository bookingRepository;

    RoomOrderManagementService underTest;


    @BeforeEach
    void setUp() {
        underTest = new RoomOrderManagementService(bookingRepository, roomRepository);
    }


    @Test
    public void testCheckAvailability() throws Exception {

        // Define test data
        String checkIn = "2023-10-01";
        String checkOut = "2023-10-03";
        int adultCount = 2;
        int childrenCount = 1;

        // Mock the behavior of bookingRepository.searchAvailability
        List<Long> availableRoomIdList = new ArrayList<>();
        availableRoomIdList.add(1L);
        availableRoomIdList.add(2L);
        when(bookingRepository.searchAvailability(checkIn, checkOut, childrenCount, adultCount)).thenReturn(availableRoomIdList);

        // Mock the behavior of roomRepository.findByIds
        RoomType roomType1 = new RoomType();
        roomType1.setRoomTypeId(1L);
        roomType1.setType("Single");
        roomType1.setRoomPrice(1000L);
        roomType1.setDescription("Single Bed");
        roomType1.setMaxChildOccupancy(0);
        roomType1.setMaxAdultOccupancy(2);

        RoomType roomType2 = new RoomType();
        roomType2.setRoomTypeId(2L);
        roomType2.setType("Double");
        roomType2.setRoomPrice(2000L);
        roomType2.setDescription("Double Bed");
        roomType2.setMaxChildOccupancy(1);
        roomType2.setMaxAdultOccupancy(2);

        Room room1 = new Room();
        room1.setRoomId(1L);
        room1.setRoomType(roomType1);

        Room room2 = new Room();
        room2.setRoomId(2L);
        room2.setRoomType(roomType2);

        List<Room> availableRoomList = new ArrayList<>();
        availableRoomList.add(room1);
        availableRoomList.add(room2);
        when(roomRepository.findByIds(availableRoomIdList)).thenReturn(availableRoomList);

        underTest.checkAvailability(checkIn, checkOut, adultCount, childrenCount);

        // Verify that searchAvailability was called with the expected arguments
        verify(bookingRepository, times(1)).searchAvailability(
                eq(checkIn), eq(checkOut), eq(childrenCount), eq(adultCount)
        );


        ArgumentCaptor<List<Long>> roomIdsCaptor = ArgumentCaptor.forClass(List.class);
        // Capture the argument passed to roomRepository.findByIds
        verify(roomRepository, times(1)).findByIds(roomIdsCaptor.capture());
        // Get the captured argument
        List<Long> capturedRoomIds = roomIdsCaptor.getValue();
        // Perform your assertions on the captured argument
        assertEquals(availableRoomIdList, capturedRoomIds);
    }

}
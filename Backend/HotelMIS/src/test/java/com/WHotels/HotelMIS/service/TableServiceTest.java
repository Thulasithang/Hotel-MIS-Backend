package com.WHotels.HotelMIS.service;
import com.WHotels.HotelMIS.model.Table;
import com.WHotels.HotelMIS.repository.TableRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class TableServiceTest {

    @Mock
    private TableRepository tableRepository;

    @InjectMocks
    private TableService tableService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testUpdateTableStatus() {
        long tableId = 1L;
        boolean waiterRequested = true;
        boolean occupied = false;

        // Create a mock Table object
        Table mockTable = new Table(tableId);

        // Configure the behavior of the tableRepository mock
        when(tableRepository.findById(tableId)).thenReturn(Optional.of(mockTable));

        // Call the method to be tested
        tableService.updateTableStatus(tableId, waiterRequested, occupied);

        // Verify that the save method was called on the mock repository
        verify(tableRepository, times(1)).save(mockTable);

        // Check if the table properties were updated correctly
        assertTrue(mockTable.isWaiterRequested());
        assertFalse(mockTable.isOccupied());
    }

    @Test
    void testUpdateTableStatusTableNotFound() {
        long tableId = 1L;
        boolean waiterRequested = true;
        boolean occupied = false;

        // Configure the behavior of the tableRepository mock to return an empty optional
        when(tableRepository.findById(tableId)).thenReturn(Optional.empty());

        // Call the method to be tested and expect an EntityNotFoundException
        assertThrows(EntityNotFoundException.class, () -> {
            tableService.updateTableStatus(tableId, waiterRequested, occupied);
        });

        // Verify that the save method was not called on the mock repository
        verify(tableRepository, never()).save(any(Table.class));
    }
}

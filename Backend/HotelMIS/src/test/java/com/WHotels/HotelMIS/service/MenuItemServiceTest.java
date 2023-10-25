package com.WHotels.HotelMIS.service;

import com.WHotels.HotelMIS.model.MenuItem;
import com.WHotels.HotelMIS.repository.MenuItemRepository;
import com.WHotels.HotelMIS.service.MenuItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class MenuItemServiceTest {

    @InjectMocks
    private MenuItemService menuItemService;

    @Mock
    private MenuItemRepository menuItemRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetInStockMenuItems() {
        // Create a list of mock in-stock menu items
        List<MenuItem> mockMenuItems = new ArrayList<>();
        mockMenuItems.add(new MenuItem(1, "Appetizer", "Item1", "Description1", 5, BigDecimal.valueOf(10.99), (short) 0,
                "image1.jpg", BigDecimal.valueOf(4.5)));
        mockMenuItems.add(new MenuItem(2, "Main Course", "Item2", "Description2", 3, BigDecimal.valueOf(15.99),
                (short) 10, "image2.jpg", BigDecimal.valueOf(4.2)));

        // Mock the repository's behavior
        when(menuItemRepository.findInStockMenuItems()).thenReturn(mockMenuItems);

        // Call the service method
        List<MenuItem> result = menuItemService.getInStockMenuItems();

        // Assertions
        assertEquals(2, result.size()); // Ensure that two in-stock menu items are returned
    }

    @Test
    public void testGetAllMenuItems() {
        // Create a list of mock menu items
        List<MenuItem> mockMenuItems = new ArrayList<>();
        mockMenuItems.add(new MenuItem(1, "Appetizer", "Item1", "Description1", 5, BigDecimal.valueOf(10.99), (short) 0,
                "image1.jpg", BigDecimal.valueOf(4.5)));
        mockMenuItems.add(new MenuItem(2, "Main Course", "Item2", "Description2", 3, BigDecimal.valueOf(15.99),
                (short) 10, "image2.jpg", BigDecimal.valueOf(4.2)));

        // Mock the repository's behavior
        when(menuItemRepository.findAll()).thenReturn(mockMenuItems);

        // Call the service method
        ResponseEntity<List<MenuItem>> response = menuItemService.getAllMenuItems();

        // Assertions
        assertEquals(2, response.getBody().size()); // Ensure that two menu items are returned
        assertTrue(response.getStatusCode().is2xxSuccessful()); // Ensure that the response status is successful (2xx)
    }

    @Test
    public void testGetMenuItemsByType() {
        // Create a mock menu item type
        String itemType = "Appetizer";

        // Create a list of mock menu items with the given type
        List<MenuItem> mockMenuItems = new ArrayList<>();
        mockMenuItems.add(new MenuItem(1, "Appetizer", "Item1", "Description1", 5, BigDecimal.valueOf(10.99), (short) 0,
                "image1.jpg", BigDecimal.valueOf(4.5)));
        mockMenuItems.add(new MenuItem(2, "Main Course", "Item2", "Description2", 3, BigDecimal.valueOf(15.99),
                (short) 10, "image2.jpg", BigDecimal.valueOf(4.2)));

        // Mock the repository's behavior
        when(menuItemRepository.findByType(itemType)).thenReturn(mockMenuItems);

        // Call the service method with the specified type
        ResponseEntity<List<MenuItem>> response = menuItemService.getMenuItemsByType(itemType);

        // Assertions
        assertEquals(2, response.getBody().size()); // Ensure that two menu items of the specified type are returned
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode()); // Check that the response status is
                                                                        // HttpStatus.BAD_REQUEST
    }

    @Test
    public void testGetDiscountMenuItems() {
        // Create a list of mock discounted menu items
        List<MenuItem> mockMenuItems = new ArrayList<>();

        mockMenuItems.add(new MenuItem(1, "Appetizer", "Item1", "Description1", 5, BigDecimal.valueOf(10.99), (short) 0,
                "image1.jpg", BigDecimal.valueOf(4.5)));
        mockMenuItems.add(new MenuItem(2, "Main Course", "Item2", "Description2", 3, BigDecimal.valueOf(15.99),
                (short) 10, "image2.jpg", BigDecimal.valueOf(4.2)));

        // Mock the repository's behavior
        when(menuItemRepository.findDiscountMenuItems()).thenReturn(mockMenuItems);

        // Call the service method
        ResponseEntity<List<MenuItem>> response = menuItemService.getDiscountMenuItems();

        // Assertions
        assertEquals(2, response.getBody().size()); // Ensure that two discounted menu items are returned
        assertTrue(response.getStatusCode().is2xxSuccessful()); // Ensure that the response status is successful (2xx)
    }
}

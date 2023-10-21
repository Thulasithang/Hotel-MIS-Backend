package com.WHotels.HotelMIS.controller;

import com.WHotels.HotelMIS.controller.MenuItemController;
import com.WHotels.HotelMIS.model.MenuItem;
import com.WHotels.HotelMIS.service.MenuItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class MenuItemControllerTest {

    @InjectMocks
    private MenuItemController menuItemController;

    @Mock
    private MenuItemService menuItemService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllMenuItems() {
        // Create a list of mock menu items
        List<MenuItem> mockMenuItems = new ArrayList<>();
        mockMenuItems.add(new MenuItem(1, "Appetizer", "Item1", "Description1", 5, BigDecimal.valueOf(10.99), (short) 0, "image1.jpg", BigDecimal.valueOf(4.5)));
        mockMenuItems.add(new MenuItem(2, "Main Course", "Item2", "Description2", 3, BigDecimal.valueOf(15.99), (short) 10, "image2.jpg", BigDecimal.valueOf(4.2)));

        // Mock the service's behavior
        when(menuItemService.getAllMenuItems()).thenReturn(ResponseEntity.ok(mockMenuItems));

        // Call the controller method
        ResponseEntity<List<MenuItem>> response = menuItemController.getAllMenuItems();

        // Assertions
        assertEquals(2, response.getBody().size()); // Ensure that two menu items are returned
        assertTrue(response.getStatusCode().is2xxSuccessful());
    }

    @Test
    public void testGetMenuItemsByType() {
        // Create a mock menu item type
        String itemType = "Appetizer";

        // Create a list of mock menu items with the given type
        List<MenuItem> mockMenuItems = new ArrayList<>();
        mockMenuItems.add(new MenuItem(1, itemType, "Item1", "Description1", 5, BigDecimal.valueOf(10.99), (short) 0, "image1.jpg", BigDecimal.valueOf(4.5)));
        mockMenuItems.add(new MenuItem(2, itemType, "Item2", "Description2", 3, BigDecimal.valueOf(9.99), (short) 5, "image2.jpg", BigDecimal.valueOf(4.0)));

        // Mock the service's behavior
        when(menuItemService.getMenuItemsByType(itemType)).thenReturn(ResponseEntity.ok(mockMenuItems));

        // Call the controller method with the specified type
        ResponseEntity<List<MenuItem>> response = menuItemController.getMenuItemsByType(itemType);

        // Assertions
        assertEquals(2, response.getBody().size()); // Ensure that two menu items of the specified type are returned
        assertTrue(response.getStatusCode().is2xxSuccessful());
    }
}

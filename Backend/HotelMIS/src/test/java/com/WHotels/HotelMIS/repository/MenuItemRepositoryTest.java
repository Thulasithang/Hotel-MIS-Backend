package com.WHotels.HotelMIS.repository;

import com.WHotels.HotelMIS.model.MenuItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class MenuItemRepositoryTest {

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Test
    public void testFindByType() {
        // Create and save mock menu items of a specific type
        MenuItem menuItem1 = new MenuItem(1, "Appetizer", "Item1", "Description1", 5, BigDecimal.valueOf(10.99), (short) 0, "image1.jpg", BigDecimal.valueOf(4.5));
        MenuItem menuItem2 = new MenuItem(2, "Appetizer", "Item2", "Description2", 3, BigDecimal.valueOf(15.99), (short) 10, "image2.jpg", BigDecimal.valueOf(4.2));
        MenuItem menuItem3 = new MenuItem(3, "Main Course", "Item3", "Description3", 4, BigDecimal.valueOf(19.99), (short) 0, "image3.jpg", BigDecimal.valueOf(4.7));
        menuItemRepository.saveAll(Arrays.asList(menuItem1, menuItem2, menuItem3));

        // Query for menu items of type "Appetizer"
        List<MenuItem> foundMenuItems = menuItemRepository.findByType("Appetizer");

        // Assertions
        assertEquals(2, foundMenuItems.size()); // Expecting 2 menu items of type "Appetizer"
    }

    @Test
    public void testFindInStockMenuItems() {
        // Create and save mock menu items with different quantities
        MenuItem menuItem1 = new MenuItem(1, "Appetizer", "Item1", "Description1", 0, BigDecimal.valueOf(10.99), (short) 0, "image1.jpg", BigDecimal.valueOf(4.5));
        MenuItem menuItem2 = new MenuItem(2, "Main Course", "Item2", "Description2", 3, BigDecimal.valueOf(15.99), (short) 10, "image2.jpg", BigDecimal.valueOf(4.2));
        MenuItem menuItem3 = new MenuItem(3, "Dessert", "Item3", "Description3", 1, BigDecimal.valueOf(8.99), (short) 0, "image3.jpg", BigDecimal.valueOf(4.0));
        menuItemRepository.saveAll(Arrays.asList(menuItem1, menuItem2, menuItem3));

        // Query for in-stock menu items
        List<MenuItem> inStockMenuItems = menuItemRepository.findInStockMenuItems();

        // Assertions
        assertEquals(2, inStockMenuItems.size()); // Expecting 2 in-stock menu items
    }

    @Test
    public void testFindDiscountMenuItems() {
        // Create and save mock menu items with different discount values
        MenuItem menuItem1 = new MenuItem(1, "Appetizer", "Item1", "Description1", 5, BigDecimal.valueOf(10.99), (short) 0, "image1.jpg", BigDecimal.valueOf(4.5));
        MenuItem menuItem2 = new MenuItem(2, "Main Course", "Item2", "Description2", 3, BigDecimal.valueOf(15.99), (short) 10, "image2.jpg", BigDecimal.valueOf(4.2));
        MenuItem menuItem3 = new MenuItem(3, "Dessert", "Item3", "Description3", 4, BigDecimal.valueOf(8.99), (short) 5, "image3.jpg", BigDecimal.valueOf(4.0));
        menuItemRepository.saveAll(Arrays.asList(menuItem1, menuItem2, menuItem3));

        // Query for menu items with a discount
        List<MenuItem> discountedMenuItems = menuItemRepository.findDiscountMenuItems();

        // Assertions
        assertEquals(2, discountedMenuItems.size()); // Expecting 2 menu items with a discount
    }
}

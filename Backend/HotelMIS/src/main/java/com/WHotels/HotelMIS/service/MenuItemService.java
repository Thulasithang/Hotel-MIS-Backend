package com.WHotels.HotelMIS.service;

import com.WHotels.HotelMIS.model.MenuItem;
import com.WHotels.HotelMIS.model.Table;
import com.WHotels.HotelMIS.repository.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuItemService {

    @Autowired
    MenuItemRepository menuItemRepository;

    public MenuItemRepository getMenuItemRepository() {
        return menuItemRepository;
    }

    public List<MenuItem> getInStockMenuItems() {
        return menuItemRepository.findInStockMenuItems();
    }

    /**
     * The function retrieves all menu items from the repository and returns them in a ResponseEntity
     * object with an HTTP status code.
     * 
     * @return The method is returning a ResponseEntity object containing a List of MenuItem objects.
     */
    public ResponseEntity<List<MenuItem>> getAllMenuItems() {
        try {
            List<MenuItem> menuItems = menuItemRepository.findAll();
            return new ResponseEntity<>(menuItems, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            ;
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<MenuItem>> getMenuItemsByType(String type) {
        try {
            return new ResponseEntity<>(menuItemRepository.findByType(type), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    /**
     * The function retrieves a list of discounted menu items from a repository and returns it as a
     * ResponseEntity.
     * 
     * @return The method is returning a ResponseEntity object containing a List of MenuItem objects.
     */
    public ResponseEntity<List<MenuItem>> getDiscountMenuItems() {
        try {
            List<MenuItem> menuItems = menuItemRepository.findDiscountMenuItems();
            return new ResponseEntity<>(menuItems, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            ;
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public void deleteMenuItemById(Integer menuitemId) {
        menuItemRepository.deleteById(menuitemId);
    }

    public ResponseEntity<MenuItem> updateMenuItem(Integer id, MenuItem updatedItem) {
        MenuItem existingItem = menuItemRepository.findById(id).orElse(null);
        if (existingItem == null) {
            return ResponseEntity.notFound().build();
        }

        // Update the existing item with the new data
        existingItem.setName(updatedItem.getName());
        existingItem.setPrice(updatedItem.getPrice());
        existingItem.setDiscount(updatedItem.getDiscount());
        existingItem.setQuantity(updatedItem.getQuantity());
        existingItem.setFoodType(updatedItem.getFoodType());
        existingItem.setImageUrl(updatedItem.getImageUrl());
        existingItem.setDescription(updatedItem.getDescription());

        // Save the updated item
        MenuItem updatedMenuItem = menuItemRepository.save(existingItem);
        return ResponseEntity.ok(updatedMenuItem);
    }

    public ResponseEntity<MenuItem> addMenuItem(MenuItem newItem) {
        MenuItem createdMenuItem = menuItemRepository.save(newItem);
        return ResponseEntity.ok(createdMenuItem);
    }
}

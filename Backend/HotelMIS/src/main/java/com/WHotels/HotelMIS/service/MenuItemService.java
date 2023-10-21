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
}

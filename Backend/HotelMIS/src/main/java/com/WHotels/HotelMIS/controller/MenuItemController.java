package com.WHotels.HotelMIS.controller;



import com.WHotels.HotelMIS.model.MenuItem;
import com.WHotels.HotelMIS.model.Table;
import com.WHotels.HotelMIS.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping(path="api/v1/menuitem")
public class MenuItemController {

    @Autowired
    private  MenuItemService menuItemService;

    /**
     * The function returns a list of all menu items as a ResponseEntity.
     * 
     * @return The method is returning a ResponseEntity object that contains a list of MenuItem
     * objects.
     */
    @GetMapping("/getAllItems")
     public ResponseEntity<List<MenuItem>> getAllMenuItems() {
        return menuItemService.getAllMenuItems();
    }

    /**
     * The function `getMenuItemsByType` returns a list of menu items based on the specified type.
     * 
     * @param type The "type" parameter is a String that represents the type of menu items to retrieve.
     * @return The method is returning a ResponseEntity object that contains a list of MenuItem
     * objects.
     */
    @GetMapping("getItemsByType/{type}")
    public ResponseEntity<List<MenuItem>> getMenuItemsByType(@PathVariable String type) {
        return menuItemService.getMenuItemsByType(type);
    }
    @GetMapping(path="/instock")
    public List<MenuItem> getInStockMenuItems(){
        return menuItemService.getInStockMenuItems();
    }

    /**
     * The function "getDiscountMenuItems" returns a list of discounted menu items.
     * 
     * @return The method is returning a ResponseEntity object that contains a list of MenuItem
     * objects.
     */
    @GetMapping("/getDiscountItems")
    public ResponseEntity <List<MenuItem>> getDiscountMenuItems(){
        return menuItemService.getDiscountMenuItems();
    }

    @DeleteMapping("/delete/{menuitemId}")
    public void deleteMenuItemId(@PathVariable Integer menuitemId) {
        System.out.println(menuitemId);
        menuItemService.deleteMenuItemById(menuitemId);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<MenuItem> updateMenuItem(@PathVariable Integer id, @RequestBody MenuItem updatedItem) {
        return menuItemService.updateMenuItem(id, updatedItem);
    }

    @PostMapping("/add")
    public ResponseEntity<MenuItem> addMenuItem(@RequestBody MenuItem newItem) {
        return menuItemService.addMenuItem(newItem);
    }




}



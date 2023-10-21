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

    // @GetMapping("/getitems")
    // public List<MenuItem> getMenuItems(){
    //     return menuItemService.getMenuItems();
    // }

    @GetMapping("/getAllItems")
     public ResponseEntity<List<MenuItem>> getAllMenuItems() {
        return menuItemService.getAllMenuItems();
    }

    @GetMapping("getItemsByType/{type}")
    public ResponseEntity<List<MenuItem>> getMenuItemsByType(@PathVariable String type) {
        return menuItemService.getMenuItemsByType(type);
    }
    @GetMapping(path="/instock")
    public List<MenuItem> getInStockMenuItems(){
        return menuItemService.getInStockMenuItems();
    }

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



package com.WHotels.HotelMIS.controller;



import com.WHotels.HotelMIS.model.MenuItem;
import com.WHotels.HotelMIS.model.Table;
import com.WHotels.HotelMIS.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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

}



package com.WHotels.HotelMIS.repository;


import com.WHotels.HotelMIS.model.MenuItem;
import com.WHotels.HotelMIS.model.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Integer> {

    @Query(value = "SELECT * FROM menu_item m WHERE m.food_type = :type", nativeQuery = true)
    List<MenuItem> findByType(String type);

    @Query("SELECT m FROM MenuItem m WHERE m.quantity > 0")
    List<MenuItem> findInStockMenuItems();

    @Query(value = "SELECT * FROM menu_item WHERE menuitem_id IN (:itemIds)", nativeQuery = true)
    List<MenuItem> getMenuItemsById(List<Integer> itemIds);

    @Query("SELECT m FROM MenuItem m WHERE m.discount > 0")
    List<MenuItem> findDiscountMenuItems();
    
}

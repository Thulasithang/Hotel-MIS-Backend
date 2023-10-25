package com.WHotels.HotelMIS.repository;


import com.WHotels.HotelMIS.model.MenuItem;
import com.WHotels.HotelMIS.model.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Integer> {

    /**
     * This function retrieves a list of menu items from the database based on the specified food type.
     */
    @Query(value = "SELECT * FROM menu_item m WHERE m.food_type = :type", nativeQuery = true)
    List<MenuItem> findByType(String type);

    @Query("SELECT m FROM MenuItem m WHERE m.quantity > 0")
    List<MenuItem> findInStockMenuItems();


    /**
     * This function retrieves menu items from the database based on a list of item IDs.
     */
    @Query(value = "SELECT * FROM menu_item WHERE menuitem_id IN (:itemIds)", nativeQuery = true)
    List<MenuItem> getMenuItemsById(List<Integer> itemIds);

    /**
     * This function retrieves a list of discounted menu items from the database.
     */
    @Query("SELECT m FROM MenuItem m WHERE m.discount > 0")
    List<MenuItem> findDiscountMenuItems();

    @Query(value = "SELECT * FROM menuitem WHERE menuitem_id IN (SELECT menu_item_id FROM order_menu_item WHERE order_id = :orderId)",
            nativeQuery = true)
    List<MenuItem> findMenuItemsByOrderId(@Param("orderId") Long orderId);
    
}

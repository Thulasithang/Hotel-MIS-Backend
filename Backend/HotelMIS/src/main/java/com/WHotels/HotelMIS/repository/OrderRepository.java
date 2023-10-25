package com.WHotels.HotelMIS.repository;

import com.WHotels.HotelMIS.model.MenuItem;
import com.WHotels.HotelMIS.model.Orders;

import jakarta.transaction.TransactionScoped;
import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders,Integer> {

    // @Modifying
    // @Transactional
    // @Query(value = "INSERT INTO orders (item_id) VALUES (:itemId)", nativeQuery = true)
    // Order saveOrder(int itemId);
    @Query(value = "SELECT * FROM orders WHERE order_status = 'placed'", nativeQuery = true)
    List<Orders> getPlacedOrders();
    @Query(value = "SELECT * FROM orders WHERE order_status = 'prepared'", nativeQuery = true)
    List<Orders> getPreparedOrders();
    // void createOrder(int itemId);

    @Query(value = "SELECT * FROM menu_item WHERE menuitem_id IN (:itemIds)", nativeQuery = true)
    List<MenuItem> getMenuItemsById(@Param("itemIds") List<Integer> itemIds);

    @Query(value = "SELECT quantity FROM order_menu_item WHERE order_id = :orderId AND menu_item_id = :menuItemId", nativeQuery = true)
    Integer findQuantityByOrderIdAndMenuItemId(@Param("orderId") Long orderId, @Param("menuItemId") Long menuItemId);


}

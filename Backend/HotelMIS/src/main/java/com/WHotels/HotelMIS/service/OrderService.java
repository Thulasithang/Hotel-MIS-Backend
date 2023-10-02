package com.WHotels.HotelMIS.service;

import com.WHotels.HotelMIS.model.MenuItem;
import com.WHotels.HotelMIS.model.OrderMenuItem;
import com.WHotels.HotelMIS.model.Orders;
import com.WHotels.HotelMIS.repository.MenuItemRepository;
import com.WHotels.HotelMIS.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {


    @Autowired
    OrderRepository orderRepository;

    @Autowired
    MenuItemRepository MenuItemRepository;

    public List<Orders> getPlacedOrders() {
        return orderRepository.getPlacedOrders();
    }

    public List<Orders> getPreparedOrders() {
        return orderRepository.getPreparedOrders();
    }

    public ResponseEntity<String> createOrder(List<Integer> itemIds, String customerName, Integer tableId, String customerNumber) {
        List<MenuItem> menuItemsOrdered = MenuItemRepository.getMenuItemsById(itemIds);
        Orders order = new Orders();
        order.setTableId(tableId);
        order.setMenuItems(menuItemsOrdered);
        order.setOrderStatus("ordered");
        order.setCustomerName(customerName);
        order.setCustomerNumber(customerNumber);
        orderRepository.save(order);
        System.out.println("This is the Order service file. Received itemIds: " + itemIds + " for tableId: " + tableId + " customerName: " + customerName + " customerNumber: " + customerNumber);
        return new ResponseEntity<>("Order created successfully", HttpStatus.CREATED);
    }

    public ResponseEntity<String> getOrderStatus(Integer orderId) {
        Optional<Orders> order = orderRepository.findById(orderId);
        String orderStatus = order.get().getOrderStatus();
        boolean isPrepared = false;
        if (orderStatus.equals("prepared")) {
            isPrepared = true;
        }
        if (isPrepared) {
            return new ResponseEntity<>("Order prepared", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Order not prepared", HttpStatus.OK);
        }
    }

    public void prepareOrder(Integer orderId) {
        Optional<Orders> order = orderRepository.findById(orderId);
        order.get().setOrderStatus("prepared");
        orderRepository.save(order.get());
    }

}

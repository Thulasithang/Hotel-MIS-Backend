package com.WHotels.HotelMIS.controller;

import com.WHotels.HotelMIS.model.MenuItem;
import com.WHotels.HotelMIS.model.Orders;
import com.WHotels.HotelMIS.service.MenuItemService;
import com.WHotels.HotelMIS.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/v1/order")
public class OrderController {

    private final OrderService orderService;
    private final MenuItemService menuItemService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;


    @Autowired
    OrderController(OrderService orderService, JdbcTemplate jdbcTemplate, MenuItemService menuItemService) {
        this.orderService = orderService;
        this.menuItemService = menuItemService;
    }

    @GetMapping("/placed")
    public List<Orders> getPlacedOrders() {
        return orderService.getPlacedOrders();
    }

    @GetMapping("/prepared")
    public List<Orders> getPreparedOrders() {
        return orderService.getPreparedOrders();
    }

    @PostMapping("/create")
    public ResponseEntity<String> createOrder(@RequestBody Map<String, Object> requestData) {
            List<Map<String, Object>> items = (List<Map<String, Object>>) requestData.get("items"); 
            Integer tableId = (Integer) requestData.get("tableId"); 
            String customerName = (String) requestData.get("customerName");
            String customerNumber = (String) requestData.get("customerNumber");
            List<Integer> itemIds = items.stream()
            .map(item -> (Integer) item.get("id"))
            .collect(Collectors.toList());

            System.out.println("Received itemIds: " + itemIds + " for tableId: "  + " customerName: " + customerName + " customerNumber: " + customerNumber);
            return orderService.createOrder(itemIds,  customerName, tableId, customerNumber);  
    }

    // @GetMapping("/status/{orderId}")
    // public ResponseEntity<String> getOrderStatus(@PathVariable Integer orderId) {
    //     return orderService.getOrderStatus(orderId);
    // }

    @PostMapping("/prepareOrder/{orderId}")
    public ResponseEntity<String> prepareOrder(@PathVariable Integer orderId) {
        orderService.prepareOrder(orderId);
        messagingTemplate.convertAndSend("/topic/order-prepared", "Order prepared: " + orderId);

        return new ResponseEntity<>("Order prepared", HttpStatus.OK);
    }

    @GetMapping("quantity/{orderId}/{menuItemId}")
    public ResponseEntity<Integer> findQuantityByOrderIdAndMenuItemId(
            @PathVariable Long orderId,
            @PathVariable Long menuItemId) {

        Integer quantity = orderService.getOrderRepository().findQuantityByOrderIdAndMenuItemId(orderId, menuItemId);

        if (quantity != null) {
            return ResponseEntity.ok(quantity);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<List<MenuItem>> findMenuItemsByOrderId(@PathVariable Long orderId) {
        System.out.println("working 1");
        List<MenuItem> menuItems = menuItemService.getMenuItemRepository().findMenuItemsByOrderId(orderId);

        if (!menuItems.isEmpty()) {
            return ResponseEntity.ok(menuItems);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{orderId}/markDelivered")
    public Orders markOrderAsDelivered(@PathVariable int orderId) {
        System.out.println(orderId);
        return orderService.updateOrderStatusToDelivered(orderId);
    }

    @PatchMapping("/{orderId}/markPrepared")
    public Orders markOrderAsPrepared(@PathVariable int orderId) {
        System.out.println(orderId);
        return orderService.updateOrderStatusToPrepared(orderId);
    }

    @PatchMapping("/{orderId}/markPreparing")
    public Orders markOrderAsPreparing(@PathVariable int orderId) {
        System.out.println(orderId);
        return orderService.updateOrderStatusToPreparing(orderId);
    }









}

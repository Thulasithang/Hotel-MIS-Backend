package com.WHotels.HotelMIS.controller;

import com.WHotels.HotelMIS.model.Orders;
import com.WHotels.HotelMIS.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/v1/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;


    @Autowired
    OrderController(OrderService orderService) {
        this.orderService = orderService;
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


}

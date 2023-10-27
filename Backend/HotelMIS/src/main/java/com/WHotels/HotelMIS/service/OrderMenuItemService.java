package com.WHotels.HotelMIS.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WHotels.HotelMIS.model.OrderMenuItem;
import com.WHotels.HotelMIS.repository.OrderMenuItemRepository;

@Service
public class OrderMenuItemService {

    private final OrderMenuItemRepository orderMenuItemRepository;

    @Autowired
    public OrderMenuItemService(OrderMenuItemRepository orderMenuItemRepository) {
        this.orderMenuItemRepository = orderMenuItemRepository;
    }

    public void saveOrderMenuItem(OrderMenuItem orderMenuItem) {
        System.out.println("This is the OrderMenuItem service file. Order id: "
                + " quantity: " + orderMenuItem.getQuantity());
        orderMenuItemRepository.save(orderMenuItem);
    }
}


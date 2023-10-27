package com.WHotels.HotelMIS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.WHotels.HotelMIS.model.OrderMenuItem;

public interface OrderMenuItemRepository extends JpaRepository <OrderMenuItem, Integer> {

    
}

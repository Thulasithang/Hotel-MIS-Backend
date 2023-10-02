package com.WHotels.HotelMIS.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@Entity
public class OrderMenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;
    private Integer menuItemId;
    private Integer quantity;

}

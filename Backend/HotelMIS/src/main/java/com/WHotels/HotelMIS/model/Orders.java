package com.WHotels.HotelMIS.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Objects;

@Entity
@Data
public class Orders {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer orderId;
    private Integer tableId;
    private String orderStatus;
    private String customerName;
    private String customerNumber;

    @ManyToMany
    private List<MenuItem> menuItems;

}

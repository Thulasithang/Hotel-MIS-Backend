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

    // @Override
    // public boolean equals(Object o) {
    //     if (this == o) return true;
    //     if (o == null || getClass() != o.getClass()) return false;
    //     Orders order = (Orders) o;
    //     return orderId == order.orderId && Objects.equals(tableId, order.tableId)  && Objects.equals(orderStatus, order.orderStatus);
    // }

    // @Override
    // public int hashCode() {
    //     return Objects.hash(orderId, tableId,  orderStatus);
    // }
}

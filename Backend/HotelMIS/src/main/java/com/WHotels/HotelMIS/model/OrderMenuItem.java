package com.WHotels.HotelMIS.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.EmbeddedId;
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
@jakarta.persistence.Table(name="order_menu_item")
public class OrderMenuItem {
    
    @EmbeddedId
    private OrderMenuItemId id; // Use the composite key class

    private Integer quantity;

}

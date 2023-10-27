package com.WHotels.HotelMIS.model;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Data
@Getter
@Setter
@Embeddable
public class OrderMenuItemId implements Serializable {
    private Integer orderId;
    private Integer menuItemId;

}


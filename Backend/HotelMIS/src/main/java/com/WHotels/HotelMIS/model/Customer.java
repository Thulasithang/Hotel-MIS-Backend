package com.WHotels.HotelMIS.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Integer customerId;
    private String nicNumber;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String phoneNo;
    private String email;

}


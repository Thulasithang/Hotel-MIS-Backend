package com.WHotels.HotelMIS.repository;

import com.WHotels.HotelMIS.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query(nativeQuery = true, value = "select customer_id from customer c where c.name = :customerNICNo")
    Long searchCustomerByNIC(String customerNICNo);

    @Query(nativeQuery = true, value = "select * from customer c where c.nic_number = :nicNo")
    List<Customer> findByNICNo(String nicNo);

    @Modifying
    @Query(nativeQuery = true, value = "ALTER TABLE customer ALTER COLUMN customer_id RESTART WITH 1")
    void resetAutoIncrement();
}

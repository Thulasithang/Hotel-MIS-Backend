package com.WHotels.HotelMIS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.WHotels.HotelMIS.model.Table;

import java.util.List;

@Repository
public interface TableRepository extends JpaRepository<Table, Long> {

    @Query(value = "SELECT * FROM restaurant_table ORDER BY table_id ASC", nativeQuery = true)
    List<Table> findByOrderByIdAsc();

}


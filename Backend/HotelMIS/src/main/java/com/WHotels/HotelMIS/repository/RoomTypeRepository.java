package com.WHotels.HotelMIS.repository;


import com.WHotels.HotelMIS.model.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoomTypeRepository extends JpaRepository<RoomType, Long> {


}

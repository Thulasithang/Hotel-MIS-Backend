package com.WHotels.HotelMIS.repository;

import com.WHotels.HotelMIS.model.Table;
import com.WHotels.HotelMIS.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}

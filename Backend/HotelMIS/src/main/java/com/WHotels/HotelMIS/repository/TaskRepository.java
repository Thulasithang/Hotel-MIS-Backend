package com.WHotels.HotelMIS.repository;

import com.WHotels.HotelMIS.model.Table;
import com.WHotels.HotelMIS.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByStaffIdAndStatusNot(long staffId, String status);
}

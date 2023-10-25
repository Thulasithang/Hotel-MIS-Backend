package com.WHotels.HotelMIS.repository;

import com.WHotels.HotelMIS.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
public interface UserRepository extends JpaRepository<Users,Long> {
    Optional<Users> findByUsername(String username);

    List<Users> findByUserRole(String staff);
}

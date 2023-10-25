package com.WHotels.HotelMIS.service;

import com.WHotels.HotelMIS.model.Users;
import com.WHotels.HotelMIS.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String addUser(Users user){
        user.setUserPassword(encoder.encode(user.getUserPassword()));
        userRepository.save(user);
        return "user added";
    }

    public List<Users> getUsers() {
        return userRepository.findAll();
    }


    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public int getUserIdByUsername(String username) {
        Optional<Users> user = userRepository.findByUsername(username);
        if (user != null) {
            return user.get().getUserId();
        }
        // Return an appropriate value or throw an exception if the user is not found.
        return -1; // For example, return -1 if the user is not found.
    }

    public List<Users> getStaffUsers() {
        return userRepository.findByUserRole("ROLE_HOTELSTAFF");
    }

    public String getUserRoleByUsername(String username) {
        Optional<Users> user = userRepository.findByUsername(username);
        if (user != null) {
            return user.get().getUserRole();
        }
        // Return an appropriate value or throw an exception if the user is not found.
        return ""; // For example, return -1 if the user is not found.
    }


}

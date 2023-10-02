package com.WHotels.HotelMIS.service;

import com.WHotels.HotelMIS.model.Users;
import com.WHotels.HotelMIS.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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


}

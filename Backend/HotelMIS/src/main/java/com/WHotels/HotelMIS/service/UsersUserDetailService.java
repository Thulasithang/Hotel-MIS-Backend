package com.WHotels.HotelMIS.service;

import com.WHotels.HotelMIS.model.Users;
import com.WHotels.HotelMIS.model.UsersUserDetails;
import com.WHotels.HotelMIS.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import java.util.Optional;
import javax.swing.text.html.Option;


@Component
public class UsersUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> user =  userRepository.findByUsername(username);
        return user.map(UsersUserDetails::new)
                .orElseThrow(()->new UsernameNotFoundException("user not found"));
    }
}

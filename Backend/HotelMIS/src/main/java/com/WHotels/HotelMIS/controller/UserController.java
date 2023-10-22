package com.WHotels.HotelMIS.controller;

import com.WHotels.HotelMIS.model.AuthRequest;
import com.WHotels.HotelMIS.model.Orders;
import com.WHotels.HotelMIS.model.Users;
import com.WHotels.HotelMIS.service.JWTService;
import com.WHotels.HotelMIS.service.OrderService;
import com.WHotels.HotelMIS.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/user")
public class UserController {

    @Autowired
    private final UserService userService;
    @Autowired
    private JWTService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public List<Users> GetUsers(){
        return userService.getUsers();
    }

    @GetMapping("/staff")
    public List<Users> GetStaffUsers(){
        return userService.getStaffUsers();

    }

    @GetMapping("/userid")
    public int getUserIdByUsername(@RequestParam String username) {
        return userService.getUserIdByUsername(username);
    }
//    @GetMapping("/{username}/userid")
//    public int getUserIdByUsername(@PathVariable String username) {
//        return userService.getUserIdByUsername(username);
//    }

    @DeleteMapping("/remove/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }



    @PostMapping("/new")
    public String addNewUser(@RequestBody Users user){
        return userService.addUser(user);

    }

    @PostMapping("/authenticate")
    public String authenticateAndGenerateToken(@RequestBody AuthRequest authRequest){
        boolean validUserRole = userService.getUserRoleByUsername(authRequest.getUsername()).equals(authRequest.getUserRole());

       Authentication authentication =authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword()));
       if(authentication.isAuthenticated() && validUserRole){
           return jwtService.generateToken(authRequest.getUsername());
       }else{
           throw new UsernameNotFoundException("invalid username and password !");
       }

    }





}

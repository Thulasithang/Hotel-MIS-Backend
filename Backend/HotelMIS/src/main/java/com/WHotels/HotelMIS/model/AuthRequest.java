package com.WHotels.HotelMIS.model;

import jdk.jfr.DataAmount;

public class AuthRequest {
    private String username;
    private String password;

    private String userRole;

    public AuthRequest(String username, String password, String userRole) {
        this.username = username;
        this.password = password;
        this.userRole = userRole;
    }

    public AuthRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public String getUserRole() {
        return userRole;
    }
    public String setUserRole(String userRole) {
        return this.userRole = userRole;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

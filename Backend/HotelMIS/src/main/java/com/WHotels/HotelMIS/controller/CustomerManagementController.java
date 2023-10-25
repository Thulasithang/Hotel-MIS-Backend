package com.WHotels.HotelMIS.controller;


import com.WHotels.HotelMIS.dto.CustomerSavedResponse;
import com.WHotels.HotelMIS.model.Customer;
import com.WHotels.HotelMIS.service.CustomerManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/customer")
@CrossOrigin
public class CustomerManagementController {

    @Autowired
    CustomerManagementService customerManagementService;

    @GetMapping("/view-customer")
    ResponseEntity<Customer> findCustomerByNIC(@RequestParam String nicNo) throws Exception{
        Customer response = customerManagementService.findCustomerByNIC(nicNo);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/submit-customer")
    ResponseEntity<CustomerSavedResponse> saveCustomer(@RequestBody Customer customer) throws Exception{
        CustomerSavedResponse response = customerManagementService.saveCustomer(customer);
        return ResponseEntity.ok(response);
    }
}

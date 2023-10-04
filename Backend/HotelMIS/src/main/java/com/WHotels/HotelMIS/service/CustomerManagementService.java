package com.WHotels.HotelMIS.service;


import com.WHotels.HotelMIS.dto.CustomerSavedResponse;
import com.WHotels.HotelMIS.model.Customer;
import com.WHotels.HotelMIS.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CustomerManagementService {
    @Autowired
    CustomerRepository customerRepository;
    public Customer findCustomerByNIC(String nicNo){
        try{
            List<Customer> customer = customerRepository.findByNICNo(nicNo);
            if(customer!=null && !customer.isEmpty()){
                return customer.get(0);
            }else {
                return new Customer();
            }

        }catch (Exception ex){
            return new Customer();
        }
    }

    public CustomerSavedResponse saveCustomer(Customer customer) throws Exception{
        try{
            Customer savedCustomer;
            List<Customer> customerList = customerRepository.findByNICNo(customer.getNicNumber());
            if(customerList!=null && !customerList.isEmpty()){
                savedCustomer = customerList.get(0);
            }else {
                savedCustomer = customerRepository.save(customerMapping(customer));
            }
            return CustomerSavedResponse.builder().customerId(savedCustomer.getCustomerId()).build();
        }catch (Exception ex){
            throw new Exception("Exception in Service Layer!");
        }
    }

    private Customer customerMapping(Customer confirmedRequest) throws Exception {
        try{
            Customer customer = new Customer();
            customer.setCustomerId(confirmedRequest.getCustomerId());
            customer.setFirstName(confirmedRequest.getFirstName());
            customer.setLastName(confirmedRequest.getLastName());
            customer.setPhoneNo(confirmedRequest.getPhoneNo());
            customer.setNicNumber(confirmedRequest.getNicNumber());
            customer.setDateOfBirth(confirmedRequest.getDateOfBirth());
            customer.setEmail(confirmedRequest.getEmail());
            return customer;
        }catch (Exception ex){
            throw new Exception("Exception In Service Layer!");
        }
    }
}

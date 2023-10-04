package com.WHotels.HotelMIS.service;

import com.WHotels.HotelMIS.dto.CustomerSavedResponse;
import com.WHotels.HotelMIS.model.Customer;
import com.WHotels.HotelMIS.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerManagementServiceTest {

    @Mock
    CustomerRepository customerRepository;

    CustomerManagementService underTest;


    @BeforeEach
    void setUp() {
        underTest = new CustomerManagementService(customerRepository);
    }


    @Test
    void findCustomerByNIC() {
        String nicNo = "123456789";

        // Create a mock Customer object to return
        Customer mockCustomer = new Customer();
        mockCustomer.setNicNumber(nicNo);

        List<Customer> customerList = List.of(mockCustomer);
        // Mock the behavior of the customerRepository.findByNICNo method
        when(customerRepository.findByNICNo(nicNo)).thenReturn(customerList);



        // Call the method under test
        underTest.findCustomerByNIC(nicNo);

        // Capture the argument passed to the customerRepository.findByNICNo method
        ArgumentCaptor<String> customerArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(customerRepository).findByNICNo(customerArgumentCaptor.capture());

        // Get the captured argument
        String capturedCustomer = customerArgumentCaptor.getValue();


        assertEquals(capturedCustomer, nicNo);

        // Verify that the customerRepository.findByNICNo method was called with the correct NIC number
        verify(customerRepository,times(1)).findByNICNo(nicNo);


    }

    @Test
    void testSaveCustomerCustomerNotExists() throws Exception {
        Customer testCustomer = new Customer();
        testCustomer.setCustomerId(1);
        testCustomer.setNicNumber("123456789");
        testCustomer.setFirstName("John");
        testCustomer.setLastName("Doe");
        testCustomer.setPhoneNo("1234567890");
        testCustomer.setDateOfBirth("01-01-2000");
        testCustomer.setEmail("john@gmail.com");

        // Set up mock behavior for findByNICNo
        // No existing customer with the same NIC number
        when(customerRepository.findByNICNo(any()))
                .thenReturn(Collections.emptyList());

        // Set up mock behavior for save
        when(customerRepository.save(any(Customer.class)))
                .thenReturn(testCustomer); // Return the saved customer

        // Call the saveCustomer method
        underTest.saveCustomer(testCustomer);

        // Verify that findByNICNo was called once with the test NIC number
        verify(customerRepository, times(1)).findByNICNo(testCustomer.getNicNumber());

        // Use ArgumentCaptor to capture the argument passed to save
        ArgumentCaptor<Customer> customerCaptor = ArgumentCaptor.forClass(Customer.class);
        verify(customerRepository, times(1)).save(customerCaptor.capture());

        // Assert the captured customer matches the test customer
        Customer capturedCustomer = customerCaptor.getValue();

        assertEquals(testCustomer.getCustomerId(), capturedCustomer.getCustomerId());

    }

    @Test
    void testSaveCustomerCustomerExists() throws Exception {
        Customer testCustomer = new Customer();
        testCustomer.setCustomerId(1);
        testCustomer.setNicNumber("123456789");
        testCustomer.setFirstName("John");
        testCustomer.setLastName("Doe");
        testCustomer.setPhoneNo("1234567890");
        testCustomer.setDateOfBirth("01-01-2000");
        testCustomer.setEmail("john@gmail.com");

        List<Customer> customerList = List.of(testCustomer);
        when(customerRepository.findByNICNo(any()))
                .thenReturn(customerList);

        // Call the saveCustomer method
        CustomerSavedResponse response =underTest.saveCustomer(testCustomer);

        // Verify that findByNICNo was called once with the test NIC number
        verify(customerRepository, times(1)).findByNICNo(testCustomer.getNicNumber());

        verify(customerRepository, never()).save(any());

        assertEquals(testCustomer.getCustomerId(), response.getCustomerId());


    }
}
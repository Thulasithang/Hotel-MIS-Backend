package com.WHotels.HotelMIS.repository;

import com.WHotels.HotelMIS.model.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository underTest;

    @BeforeEach
    void setUp() {
        underTest.resetAutoIncrement();
    }

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void checkFindByNICNo() {
        Customer customer1 = new Customer();
        customer1.setNicNumber("1234567890");
        customer1.setFirstName("John");
        customer1.setLastName("Doe");
        customer1.setDateOfBirth("1990-01-15");
        customer1.setPhoneNo("+123456789");
        customer1.setEmail("johndoe@example.com");
        underTest.save(customer1);

        Customer customer2 = new Customer();
        customer2.setNicNumber("132453232");
        customer2.setFirstName("Jane");
        customer2.setLastName("David");
        customer2.setDateOfBirth("1990-02-15");
        customer2.setPhoneNo("+123456789");
        customer2.setEmail("janedavid@gmail.com");
        underTest.save(customer2);

        String nicNo = customer1.getNicNumber();
        String nonExistNicNo = "4545454545";

        List<Customer> customerList = underTest.findByNICNo(nicNo);
        List<Integer> customerIds = customerList.stream().map(Customer::getCustomerId).toList();
        List<Integer> expected = Arrays.asList(customer1.getCustomerId());


        List<Customer> customerList1 = underTest.findByNICNo(nonExistNicNo);


//        test for customer existence
        assertEquals(expected, customerIds);

//        test for non existence
        assertTrue(customerList1.isEmpty());
    }
}
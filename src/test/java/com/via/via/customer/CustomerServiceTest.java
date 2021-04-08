package com.via.via.customer;

import static org.junit.jupiter.api.Assertions.*;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;


    @Test
    public void createTest() {
        when(customerRepository.save(any(Customer.class))).thenReturn(new Customer());

        customerService.create(new Customer());
    }

    @Test
    public void findAll() {
        when(customerRepository.findAll(any(Pageable.class))).thenReturn(Page.empty());
        customerService.findAll(0, 10);
    }

    @Test
    public void retrieveTest() {
        when(customerRepository.findById(any(ObjectId.class))).thenReturn(Optional.empty());
        customerService.retrieve(ObjectId.get());
    }

    @Test
    public void updateTest() {
        Customer customer = new Customer();
        customer.setId(ObjectId.get());

        when(customerRepository.existsById(any(ObjectId.class))).thenReturn(true);
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        boolean updated = customerService.update(customer);

        assertTrue(updated);
    }

    @Test
    public void createdByUpdateTest() {
        Customer customer = new Customer();
        customer.setId(ObjectId.get());

        when(customerRepository.existsById(any(ObjectId.class))).thenReturn(false);
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        boolean updated = customerService.update(customer);

        assertFalse(updated);
    }

    @Test
    public void createdByUpdateIdNullTest() {
        when(customerRepository.save(any(Customer.class))).thenReturn(new Customer());
        boolean updated = customerService.update(new Customer());

        assertFalse(updated);
    }

    @Test
    public void deleteTest() {
        when(customerRepository.existsById(any(ObjectId.class))).thenReturn(true);
        doNothing().when(customerRepository).deleteById(any(ObjectId.class));
        boolean deleted = customerService.delete(ObjectId.get());

        assertTrue(deleted);
    }

    @Test
    public void deleteNotFoundTest() {
        when(customerRepository.existsById(any(ObjectId.class))).thenReturn(false);
        boolean deleted = customerService.delete(ObjectId.get());

        assertFalse(deleted);
    }

}

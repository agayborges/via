package com.via.via.customer;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

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
    public void retrieveTest() {
        when(customerRepository.findById(any(UUID.class))).thenReturn(Optional.empty());
        customerService.retrieve(UUID.randomUUID());
    }

    @Test
    public void updateTest() {
        when(customerRepository.existsById(any(UUID.class))).thenReturn(true);
        when(customerRepository.save(any(Customer.class))).thenReturn(new Customer());
        boolean updated = customerService.update(new Customer());

        assertTrue(updated);
    }

    @Test
    public void createdByUpdateTest() {
        when(customerRepository.existsById(any(UUID.class))).thenReturn(false);
        when(customerRepository.save(any(Customer.class))).thenReturn(new Customer());
        boolean updated = customerService.update(new Customer());

        assertFalse(updated);
    }

    @Test
    public void createdByUpdateIdNullTest() {
        Customer customer = new Customer();
        customer.setId(null);
        when(customerRepository.existsById(any(UUID.class))).thenReturn(false);
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        boolean updated = customerService.update(new Customer());

        assertFalse(updated);
    }

    @Test
    public void deleteTest() {
        when(customerRepository.existsById(any(UUID.class))).thenReturn(true);
        doNothing().when(customerRepository).deleteById(any(UUID.class));
        boolean deleted = customerService.delete(UUID.randomUUID());

        assertTrue(deleted);
    }

    @Test
    public void deleteNotFoundTest() {
        when(customerRepository.existsById(any(UUID.class))).thenReturn(false);
        boolean deleted = customerService.delete(UUID.randomUUID());

        assertFalse(deleted);
    }

}

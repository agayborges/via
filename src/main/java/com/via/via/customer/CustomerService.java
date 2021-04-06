package com.via.via.customer;

import com.via.via.customer.Customer;
import com.via.via.policy.Policy;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;


@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    public void create(Customer customer) {
        customerRepository.save(customer);
    }

    public Optional<Customer> retrieve(UUID id) {
        return customerRepository.findById(id);
    }

    public boolean update(Customer customer) {
        UUID id = customer.getId();
        boolean hasCustomer = false;

        if (id != null) {
            hasCustomer = customerRepository.existsById(id);
        }

        customerRepository.save(customer);
        return hasCustomer;
    }

    public boolean delete(UUID id) {
        boolean hasCustomer = customerRepository.existsById(id);
        if (hasCustomer) {
            customerRepository.deleteById(id);
        }
        return hasCustomer;
    }
}

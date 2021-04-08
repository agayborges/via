package com.via.via.customer;

import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CustomerService {

    private CustomerRepository customerRepository;

    public ObjectId create(Customer customer) {

        customerRepository.save(customer);

        return customer.getId();
    }

    public Page<Customer> findAll(int page, int size) {
        return customerRepository.findAll(PageRequest.of(page, size));
    }

    public Optional<Customer> retrieve(ObjectId id) {
        return customerRepository.findById(id);
    }

    public boolean update(Customer customer) {
        ObjectId id = customer.getId();
        boolean hasCustomer = false;

        if (id != null) {
            hasCustomer = customerRepository.existsById(id);
        }

        customerRepository.save(customer);
        return hasCustomer;
    }

    public boolean delete(ObjectId id) {
        boolean hasCustomer = customerRepository.existsById(id);
        if (hasCustomer) {
            customerRepository.deleteById(id);
        }
        return hasCustomer;
    }
}

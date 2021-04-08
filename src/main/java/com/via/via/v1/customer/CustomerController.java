package com.via.via.v1.customer;

import com.via.via.customer.Customer;
import com.via.via.customer.CustomerService;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/customers")
@AllArgsConstructor
public class CustomerController {

    private CustomerService customerService;

    @PostMapping
    public ResponseEntity create(CustomerDto customerDto) {
        Customer customer = customerDto.toCustomer();
        ObjectId id = customerService.create(customer);
        return ResponseEntity.created(URI.create(id.toString())).build();
    }

    @GetMapping
    public ResponseEntity<Page<CustomerDto>> findAll(@RequestParam(name = "page", defaultValue = "0") @Min(0) int page,
                                  @RequestParam(name = "size", defaultValue = "10") @Min(1) int size) {
        Page<Customer> customers = customerService.findAll(page, size);

        Page<CustomerDto> customerDtos = customers.map(customer -> new CustomerDto(customer));

        return ResponseEntity.ok(customerDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> get(@PathVariable("id") ObjectId id) {
        Optional<Customer> customer = customerService.retrieve(id);

        if (customer.isPresent()) {
            CustomerDto dto = new CustomerDto(customer.get());
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") ObjectId id, @RequestBody CustomerDto customerDto) {
        if (!id.equals(customerDto.getId())) {
            return ResponseEntity.badRequest().build();
        }

        Customer customer = customerDto.toCustomer();
        boolean updated = customerService.update(customer);
        if (updated) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.created(URI.create(customer.getId().toString())).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") ObjectId id) {
        boolean deleted = customerService.delete(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }


}

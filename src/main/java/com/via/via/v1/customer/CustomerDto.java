package com.via.via.v1.customer;

import com.via.via.customer.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomerDto {

    private ObjectId id;

    @NotBlank
    @CPF
    private String cpf;

    @NotBlank
    private String name;

    @NotBlank
    private String city;

    @NotBlank
    private String uf;

    public CustomerDto(Customer customer) {
        this(customer.getId(), customer.getCpf(), customer.getName(), customer.getCity(), customer.getUf());
    }

    public Customer toCustomer() {
        return new Customer(id, cpf, name, city, uf);
    }
}

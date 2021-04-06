package com.via.via.customer;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.UUID;

/**
 * Cliente
 */
@Data
public class Customer {

    @Id
    private UUID id = UUID.randomUUID();
    private String cpf;
    private String name;
    private String city;
    private String uf;



}

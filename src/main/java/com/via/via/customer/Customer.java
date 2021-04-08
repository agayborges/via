package com.via.via.customer;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Cliente
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Customer {

    @Id
    private ObjectId id;

    @Indexed(unique=true)
    private String cpf;

    private String name;
    private String city;
    private String uf;



}

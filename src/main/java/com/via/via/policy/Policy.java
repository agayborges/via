package com.via.via.policy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Apolice
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Policy {

    @Id
    private ObjectId number;
    private String licencePlate;
    private BigInteger value;
    private LocalDate startDate;
    private LocalDate endDate;



}

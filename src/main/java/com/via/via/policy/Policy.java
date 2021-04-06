package com.via.via.policy;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Apolice
 */
@Data
public class Policy {

    @Id
    private UUID id = UUID.randomUUID();
    private String number;
    private String licencePlate;
    private BigInteger value;
    private LocalDateTime startDate;
    private LocalDateTime endDate;



}

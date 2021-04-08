package com.via.via.v1.policy;

import com.via.via.policy.Policy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PolicyDto {

    private UUID number;

    @NotBlank
    private String licencePlate;

    @NotBlank
    private BigInteger value;

    @NotBlank
    protected LocalDate startDate;

    @NotBlank
    protected LocalDate endDate;

    public PolicyDto(Policy policy) {
        this(policy.getNumber(), policy.getLicencePlate(), policy.getValue(),
                policy.getStartDate(), policy.getEndDate());
    }

    public Policy toPolicy() {
        return new Policy(number, licencePlate, value, startDate, endDate);
    }
}

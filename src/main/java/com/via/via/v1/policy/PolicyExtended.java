package com.via.via.v1.policy;

import com.via.via.policy.Policy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import static java.time.temporal.ChronoUnit.DAYS;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PolicyExtended extends PolicyDto {

    private boolean valid;
    private boolean expired;
    private long daysToOrFromEndDate;


    public PolicyExtended(Policy policy) {
        super(policy.getNumber(), policy.getLicencePlate(), policy.getValue(),
                policy.getStartDate(), policy.getEndDate());

        LocalDate now = LocalDate.now();
        if (now.compareTo(endDate) > 1) {
            expired = true;
            valid = false;
        } else if (now.compareTo(startDate) > 1 && now.compareTo(endDate) < 1) {
            valid = true;
            expired = true;
        }
        daysToOrFromEndDate = DAYS.between(now, endDate);
    }

    @Deprecated
    public Policy toDomain() {
        return null;
    }

}

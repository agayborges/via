package com.via.via.policy;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class PolicyService {

    private PolicyRepository policyRepository;

    public void create(Policy policy) {
        policyRepository.save(policy);
    }

    public Optional<Policy> retrieve(UUID id) {
        return policyRepository.findById(id);
    }

    public boolean update(Policy policy) {
        boolean hasPolicy = policyRepository.existsById(policy.getId());
        policyRepository.save(policy);
        return hasPolicy;
    }

    public boolean delete(UUID id) {
        boolean hasPolicy = policyRepository.existsById(id);

        if (hasPolicy) {
            policyRepository.deleteById(id);
        }

        return hasPolicy;
    }
}

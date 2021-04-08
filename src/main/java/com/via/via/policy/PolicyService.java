package com.via.via.policy;

import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class PolicyService {

    private PolicyRepository policyRepository;

    public void create(Policy policy) {
        policyRepository.save(policy);
    }

    public Optional<Policy> retrieve(ObjectId id) {
        return policyRepository.findById(id);
    }

    public boolean update(Policy policy) {
        boolean hasPolicy = policyRepository.existsById(policy.getNumber());
        policyRepository.save(policy);
        return hasPolicy;
    }

    public boolean delete(ObjectId id) {
        boolean hasPolicy = policyRepository.existsById(id);

        if (hasPolicy) {
            policyRepository.deleteById(id);
        }

        return hasPolicy;
    }
}

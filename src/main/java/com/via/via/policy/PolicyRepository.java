package com.via.via.policy;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface PolicyRepository extends MongoRepository<Policy, UUID> {

    public Optional<Policy> findByNumber(String number);

}

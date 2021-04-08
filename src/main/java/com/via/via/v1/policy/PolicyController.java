package com.via.via.v1.policy;

import com.via.via.policy.Policy;
import com.via.via.policy.PolicyService;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/policies")
@AllArgsConstructor
public class PolicyController {

    private PolicyService policyService;
    
    @PostMapping
    public ResponseEntity create(PolicyDto policyDto) {
        Policy policy = policyDto.toPolicy();
        policyService.create(policy);
        return ResponseEntity.created(URI.create(policy.getNumber().toString())).build();
    }

    @GetMapping("/{number}")
    public ResponseEntity<PolicyDto> get(@PathVariable("number") ObjectId number) {
        Optional<Policy> policy = policyService.retrieve(number);

        if (policy.isPresent()) {
            PolicyDto dto = new PolicyDto(policy.get());
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PutMapping("/{number}")
    public ResponseEntity update(@PathVariable("number") ObjectId number, @RequestBody PolicyDto policyDto) {
        Policy policy = policyDto.toPolicy();
        boolean updated = policyService.update(policy);

        if (updated) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.created(URI.create(policy.getNumber().toString())).build();
        }
    }

    @GetMapping("/{number}/extended")
    public ResponseEntity<PolicyExtended> getExtended(@PathVariable("number") ObjectId number) {
        Optional<Policy> policy = policyService.retrieve(number);

        if (policy.isPresent()) {
            PolicyExtended extended = new PolicyExtended(policy.get());
            return ResponseEntity.ok(extended);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}

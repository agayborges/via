package com.via.via.policy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PolicyServiceTest {


    @InjectMocks
    private PolicyService policyService;

    @Mock
    private PolicyRepository policyRepository;


    @Test
    public void createTest() {
        when(policyRepository.save(any(Policy.class))).thenReturn(new Policy());

        policyService.create(new Policy());
    }

    @Test
    public void retrieveTest() {
        when(policyRepository.findById(any(UUID.class))).thenReturn(Optional.empty());
        policyService.retrieve(UUID.randomUUID());
    }

    @Test
    public void updateTest() {
        when(policyRepository.existsById(any(UUID.class))).thenReturn(true);
        when(policyRepository.save(any(Policy.class))).thenReturn(new Policy());
        boolean updated = policyService.update(new Policy());

        assertTrue(updated);
    }

    @Test
    public void createdByUpdateTest() {
        when(policyRepository.existsById(any(UUID.class))).thenReturn(false);
        when(policyRepository.save(any(Policy.class))).thenReturn(new Policy());
        boolean updated = policyService.update(new Policy());

        assertFalse(updated);
    }

    @Test
    public void createdByUpdateIdNullTest() {
        Policy policy = new Policy();
        policy.setId(null);
        when(policyRepository.existsById(any(UUID.class))).thenReturn(false);
        when(policyRepository.save(any(Policy.class))).thenReturn(policy);
        boolean updated = policyService.update(new Policy());

        assertFalse(updated);
    }

    @Test
    public void deleteTest() {
        when(policyRepository.existsById(any(UUID.class))).thenReturn(true);
        doNothing().when(policyRepository).deleteById(any(UUID.class));
        boolean deleted = policyService.delete(UUID.randomUUID());

        assertTrue(deleted);
    }

    @Test
    public void deleteNotFoundTest() {
        when(policyRepository.existsById(any(UUID.class))).thenReturn(false);
        boolean deleted = policyService.delete(UUID.randomUUID());

        assertFalse(deleted);
    }
}

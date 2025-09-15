package com.example.carins.web;

import com.example.carins.model.InsuranceClaim;
import com.example.carins.model.InsurancePolicy;
import com.example.carins.service.InsurancePolicyService;
import com.example.carins.web.dto.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class InsurancePolicyController {

    private final  InsurancePolicyService insurancePolicyService;

    public InsurancePolicyController(InsurancePolicyService insurancePolicyService) {
        this.insurancePolicyService = insurancePolicyService;
    }

    @PostMapping("/policies")
    public ResponseEntity<InsurancePolicyDto> createPolicy(@Valid @RequestBody InsurancePolicyCreateDto dto){
        InsurancePolicy savedPolicy = insurancePolicyService.createPolicy(dto);
        InsurancePolicyDto responseDto = toDto(savedPolicy);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PutMapping("/policies/{id}")
    public ResponseEntity<InsurancePolicyDto> updatePolicy(@PathVariable Long id, @Valid @RequestBody InsurancePolicyUpdateDto dto){
        InsurancePolicy updatedInsurancePolicy = insurancePolicyService.updatePolicy(id, dto);
        InsurancePolicyDto responseDto = toDto(updatedInsurancePolicy);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/policies/{policyId}/claims")
    public ResponseEntity<InsuranceClaimDto> createClaim(@PathVariable Long policyId, @Valid @RequestBody InsuranceClaimDto dto){
        InsuranceClaim insuranceClaim = insurancePolicyService.createClaim(policyId, dto);
        InsuranceClaimDto responseDto = toDto(insuranceClaim);
        return ResponseEntity.ok(responseDto);
    }

    private InsuranceClaimDto toDto(InsuranceClaim insuranceClaim){
        return new InsuranceClaimDto(
                insuranceClaim.getCalimDate(),
                insuranceClaim.getDescription(),
                insuranceClaim.getAmount()
        );
    }

    private InsurancePolicyDto toDto(InsurancePolicy insurancePolicy){
        return new InsurancePolicyDto(
                insurancePolicy.getId(),
                insurancePolicy.getCar().getId(),
                insurancePolicy.getProvider(),
                insurancePolicy.getStartDate(),
                insurancePolicy.getEndDate()
        );
    }
}

package com.example.carins.service;

import com.example.carins.exception.CarNotFoundException;
import com.example.carins.exception.InvalidDateException;
import com.example.carins.exception.PolicyNotFoundException;
import com.example.carins.model.*;
import com.example.carins.repo.CarHistoryRepository;
import com.example.carins.repo.CarRepository;
import com.example.carins.repo.InsuranceClaimRepository;
import com.example.carins.repo.InsurancePolicyRepository;
import com.example.carins.web.dto.InsuranceClaimDto;
import com.example.carins.web.dto.InsurancePolicyCreateDto;
import com.example.carins.web.dto.InsurancePolicyUpdateDto;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Transactional
public class InsurancePolicyService {

    private final InsurancePolicyRepository insurancePolicyRepository;
    private final CarRepository carRepository;
    private final InsuranceClaimRepository insuranceClaimRepository;
    private final CarHistoryRepository carHistoryRepository;

    public InsurancePolicyService(InsurancePolicyRepository insurancePolicyRepository, CarRepository carRepository,
                                  InsuranceClaimRepository insuranceClaimRepository,  CarHistoryRepository carHistoryRepository) {
        this.insurancePolicyRepository = insurancePolicyRepository;
        this.carRepository = carRepository;
        this.insuranceClaimRepository = insuranceClaimRepository;
        this.carHistoryRepository = carHistoryRepository;
    }

    public InsurancePolicy createPolicy(@Valid InsurancePolicyCreateDto dto) {

        Car car = carRepository.findById(dto.getCarId())
                .orElseThrow(() -> new CarNotFoundException("Car with id " + dto.getCarId() + " not found"));

        validateDateRange(dto.getStartDate(), dto.getEndDate());

        InsurancePolicy policy = new InsurancePolicy(car, dto.getProvider(), dto.getStartDate(), dto.getEndDate());

        InsurancePolicy savedPolicy = insurancePolicyRepository.save(policy);

        CarHistory carHistory = new CarHistory(car.getId(), savedPolicy.getId(), EntityType.INSURANCE, ActionType.CREATE, "Created policy");

        carHistoryRepository.save(carHistory);

        return savedPolicy;
    }

    public InsurancePolicy updatePolicy(Long id, InsurancePolicyUpdateDto dto) {
        InsurancePolicy policy = insurancePolicyRepository.findById(id)
                .orElseThrow(() -> new PolicyNotFoundException("Policy with id " + id + " not found"));

        if(dto.getProvider()!=null && !dto.getProvider().trim().isEmpty())
            policy.setProvider(dto.getProvider().trim());

        if(dto.getEndDate() != null){
            validateDateRange(policy.getStartDate(), dto.getEndDate());
            policy.setEndDate(dto.getEndDate());
        }

        CarHistory carHistory = new CarHistory(policy.getCar().getId(), id, EntityType.INSURANCE, ActionType.UPDATE, "Updated policy");

        carHistoryRepository.save(carHistory);

        return  insurancePolicyRepository.save(policy);
    }

    public InsuranceClaim createClaim(Long policyId, InsuranceClaimDto dto){
        InsurancePolicy insurancePolicy = insurancePolicyRepository.findById(policyId)
                .orElseThrow(() -> new PolicyNotFoundException("Policy with id " + policyId + " not found"));

        InsuranceClaim insuranceClaim = new InsuranceClaim(dto.getClaimDate(), dto.getDescription(), dto.getAmount(), insurancePolicy);

        InsuranceClaim savedClaim = insuranceClaimRepository.save(insuranceClaim);

        CarHistory carHistory = new CarHistory(insurancePolicy.getCar().getId(), policyId, EntityType.CLAIM, ActionType.CREATE, "Created claim");

        carHistoryRepository.save(carHistory);

        return savedClaim;
    }

    public InsurancePolicy findPolicy(Long id) {
        return insurancePolicyRepository.findById(id)
                .orElseThrow(() -> new PolicyNotFoundException("Policy with id " + id + " not found"));
    }

    private void validateDateRange(LocalDate startDate, LocalDate endDate) {
        if( endDate != null && !endDate.isAfter(startDate) ) {
            throw new InvalidDateException("End date must be after start date");}
    }
}

package com.example.carins.service;

import com.example.carins.exception.CarNotFoundException;
import com.example.carins.exception.InvalidDateException;
import com.example.carins.exception.PolicyNotFoundException;
import com.example.carins.model.Car;
import com.example.carins.model.InsurancePolicy;
import com.example.carins.repo.CarRepository;
import com.example.carins.repo.InsurancePolicyRepository;
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

    public InsurancePolicyService(InsurancePolicyRepository insurancePolicyRepository, CarRepository carRepository) {
        this.insurancePolicyRepository = insurancePolicyRepository;
        this.carRepository = carRepository;
    }

    public InsurancePolicy createPolicy(@Valid InsurancePolicyCreateDto dto) {

        Car car = carRepository.findById(dto.getCarId())
                .orElseThrow(() -> new CarNotFoundException("Car with id " + dto.getCarId() + " not found"));

        validateDateRange(dto.getStartDate(), dto.getEndDate());

        InsurancePolicy policy = new InsurancePolicy(car, dto.getProvider(), dto.getStartDate(), dto.getEndDate());

        return insurancePolicyRepository.save(policy);
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

        return  insurancePolicyRepository.save(policy);
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

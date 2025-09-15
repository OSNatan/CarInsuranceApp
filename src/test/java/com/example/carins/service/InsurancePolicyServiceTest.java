package com.example.carins.service;

import com.example.carins.exception.PolicyNotFoundException;
import com.example.carins.model.Car;
import com.example.carins.model.InsurancePolicy;
import com.example.carins.repo.CarHistoryRepository;
import com.example.carins.repo.CarRepository;
import com.example.carins.repo.InsuranceClaimRepository;
import com.example.carins.repo.InsurancePolicyRepository;
import com.example.carins.web.dto.InsurancePolicyCreateDto;
import com.example.carins.web.dto.InsurancePolicyUpdateDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class InsurancePolicyServiceTest {

    @Mock
    private CarHistoryRepository carHistoryRepository;

    @Mock
    private CarRepository carRepository;

    @Mock
    private InsurancePolicyRepository insurancePolicyRepository;

    @Mock
    private InsuranceClaimRepository insuranceClaimRepository;

    @InjectMocks
    private InsurancePolicyService insurancePolicyService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createPolicy(){
        Car car = new Car();
        car.setId(1L);

        when(carRepository.findById(1L)).thenReturn(Optional.of(car));

        InsurancePolicy savedPolicy = new InsurancePolicy(car, "Generalli", LocalDate.now(), LocalDate.now().plusDays(1));

        when(insurancePolicyRepository.save(any())).thenReturn(savedPolicy);

        InsurancePolicy result = insurancePolicyService.createPolicy(new InsurancePolicyCreateDto(1L, "Generalli", LocalDate.now(), LocalDate.now().plusDays(1)));

        assertEquals("Generalli", result.getProvider());

        verify(carHistoryRepository).save(any());
    }

    @Test
    public void updatePolicy(){
        when(insurancePolicyRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(PolicyNotFoundException.class, () -> insurancePolicyService.updatePolicy(1L, new InsurancePolicyUpdateDto(LocalDate.now().plusDays(5), "Auto")));
    }
}

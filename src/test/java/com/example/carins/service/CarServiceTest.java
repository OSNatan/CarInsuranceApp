package com.example.carins.service;

import com.example.carins.model.ActionType;
import com.example.carins.model.Car;
import com.example.carins.model.CarHistory;
import com.example.carins.model.EntityType;
import com.example.carins.repo.CarHistoryRepository;
import com.example.carins.repo.CarRepository;
import com.example.carins.repo.InsurancePolicyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    @Mock
    private InsurancePolicyRepository insurancePolicyRepository;

    @Mock
    private CarHistoryRepository carHistoryRepository;

    @InjectMocks
    private CarService carService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void insuraceValid(){
        Long carId = 1L;
        LocalDate date = LocalDate.of(2025, 1, 2);

        when(carRepository.findById(carId)).thenReturn(Optional.of(new Car()));
        when(insurancePolicyRepository.existsActiveOnDate(carId, date)).thenReturn(true);

        boolean result = carService.isInsuranceValid(carId, date);

        assertTrue(result);
    }

    @Test
    void getHistory(){
        Long carId = 1L;

        List<CarHistory> mockedHistory = List.of(new CarHistory(1L, 1L, EntityType.INSURANCE, ActionType.CREATE, "Created policy."), new CarHistory(1L, 2L, EntityType.CLAIM, ActionType.CREATE, "New insurance claim"));

        when(carHistoryRepository.findAllByCarId(carId)).thenReturn(mockedHistory);

        List<CarHistory> result = carService.getCarHistory(carId);

        assertEquals(result.size(), mockedHistory.size());
    }
}

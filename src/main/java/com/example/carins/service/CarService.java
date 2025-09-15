package com.example.carins.service;

import com.example.carins.exception.CarNotFoundException;
import com.example.carins.exception.InvalidDateException;
import com.example.carins.model.Car;
import com.example.carins.model.CarHistory;
import com.example.carins.repo.CarHistoryRepository;
import com.example.carins.repo.CarRepository;
import com.example.carins.repo.InsurancePolicyRepository;
import com.example.carins.web.dto.CarHistoryDto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CarService {

    private final CarRepository carRepository;
    private final InsurancePolicyRepository policyRepository;
    private final CarHistoryRepository carHistoryRepository;

    public CarService(CarRepository carRepository, InsurancePolicyRepository policyRepository, CarHistoryRepository carHistoryRepository) {
        this.carRepository = carRepository;
        this.policyRepository = policyRepository;
        this.carHistoryRepository = carHistoryRepository;
    }

    public List<Car> listCars() {
        return carRepository.findAll();
    }

    public boolean isInsuranceValid(Long carId, LocalDate date) {
        if (carId == null || date == null) return false;

        if(carRepository.findById(carId).isEmpty())
            throw new CarNotFoundException("Car not found");

        if(date.isAfter(LocalDate.now().plusYears(50)) || date.isBefore(LocalDate.now().minusYears(50))){
            throw new InvalidDateException("Date must be within the valid range");
        }

        return policyRepository.existsActiveOnDate(carId, date);
    }

    public List<CarHistory> getCarHistory(Long carId) {
        return carHistoryRepository.findAllByCarId(carId);
    }
}

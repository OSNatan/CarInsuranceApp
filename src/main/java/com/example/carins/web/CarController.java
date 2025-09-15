package com.example.carins.web;

import com.example.carins.exception.CarNotFoundException;
import com.example.carins.model.Car;
import com.example.carins.model.CarHistory;
import com.example.carins.repo.CarRepository;
import com.example.carins.service.CarService;
import com.example.carins.web.dto.CarDto;
import com.example.carins.web.dto.CarHistoryDto;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CarController {

    private final CarService carService;
    private final CarRepository carRepository;

    public CarController(CarService service,  CarRepository carRepository) {

        this.carRepository = carRepository;
        this.carService = service;
    }

    @GetMapping("/cars")
    public List<CarDto> getCars() {
        return carService.listCars().stream().map(this::toDto).toList();
    }

    @GetMapping("/cars/{carId}/insurance-valid")
    public ResponseEntity<?> isInsuranceValid(@PathVariable @Valid Long carId, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String date) {
        LocalDate d = LocalDate.parse(date);
        boolean valid = carService.isInsuranceValid(carId, d);
        return ResponseEntity.ok(new InsuranceValidityResponse(carId, d.toString(), valid));
    }

    @GetMapping("/cars/history/{carId}")
    public ResponseEntity<List<CarHistoryDto>> getHistory(@PathVariable Long carId) {
        List<CarHistory> carHistories = carService.getCarHistory(carId);
        List<CarHistoryDto> carHistoriesDto = toDto(carHistories);
        return ResponseEntity.ok(carHistoriesDto);
    }

    private List<CarHistoryDto> toDto(List<CarHistory> carHistories) {
        List<CarHistoryDto> dtos = new ArrayList<>();
        for (CarHistory carHistory : carHistories) {
            CarHistoryDto dto = new CarHistoryDto(
                    carHistory.getId(),
                    carHistory.getCarId(),
                    carHistory.getEntityId(),
                    carHistory.getEntityType(),
                    carHistory.getActionType(),
                    carHistory.getInfo()
            );
            dtos.add(dto);
        }
        return dtos;
    }

    private CarDto toDto(Car c) {
        var o = c.getOwner();
        return new CarDto(c.getId(), c.getVin(), c.getMake(), c.getModel(), c.getYearOfManufacture(),
                o != null ? o.getId() : null,
                o != null ? o.getName() : null,
                o != null ? o.getEmail() : null);
    }

    public record InsuranceValidityResponse(Long carId, String date, boolean valid) {}
}

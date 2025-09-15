package com.example.carins.controler;

import com.example.carins.model.Car;
import com.example.carins.service.CarService;
import com.example.carins.web.CarController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CarControllerTest {

    @Mock
    private CarService carService;

    @InjectMocks
    private CarController carController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getCars() {
        Car car1 = new Car(); car1.setId(1L);
        Car car2 = new Car(); car2.setId(2L);

        when(carService.listCars()).thenReturn(List.of(car1, car2));

        List<?> result = carController.getCars();

        assertEquals(2, result.size());
        verify(carService, times(1)).listCars();
    }
}
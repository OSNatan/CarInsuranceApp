package com.example.carins.repo;

import com.example.carins.model.CarHistory;
import com.example.carins.model.EntityType;
import com.example.carins.web.dto.CarHistoryDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarHistoryRepository extends JpaRepository<CarHistory, Long> {

    List<CarHistory> findAllByCarId(Long carId);
}

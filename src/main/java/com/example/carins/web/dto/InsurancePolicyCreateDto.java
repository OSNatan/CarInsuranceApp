package com.example.carins.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class InsurancePolicyCreateDto {

    @NotNull
    private Long carId;

    @NotBlank
    private String provider;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    public InsurancePolicyCreateDto() {}

    public InsurancePolicyCreateDto(Long carId, String provider,  LocalDate startDate, LocalDate endDate) {
        this.carId = carId;
        this.provider = provider;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getCarId() {return carId;}
    public void setCarId(Long carId) {this.carId = carId;}
    public String getProvider() {return provider;}
    public void setProvider(String provider) {this.provider = provider;}
    public LocalDate getStartDate() {return startDate;}
    public void setStartDate(LocalDate startDate) {this.startDate = startDate;}
    public LocalDate getEndDate() {return endDate;}
    public void setEndDate(LocalDate endDate) {this.endDate = endDate;}
}

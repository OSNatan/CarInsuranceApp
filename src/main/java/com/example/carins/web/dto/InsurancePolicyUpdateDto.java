package com.example.carins.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public class InsurancePolicyUpdateDto {

    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate endDate;

    @NotBlank
    String provider;

    public InsurancePolicyUpdateDto(LocalDate endDate, String provider) {
        this.endDate = endDate;
        this.provider = provider;
    }

    public InsurancePolicyUpdateDto() {}
    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
    public String getProvider() { return provider; }
    public void setProvider(String provider) { this.provider = provider; }

}

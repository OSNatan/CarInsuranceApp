package com.example.carins.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class InsuranceClaim {

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate calimDate;

    @NotBlank
    private String description;

    @NotNull
    private Long amount;

    @ManyToOne
    private InsurancePolicy policy;

    public InsuranceClaim() {}

    public InsuranceClaim(LocalDate calimDate, String description, Long amount) {
        this.calimDate = calimDate;
        this.description = description;
        this.amount = amount;
    }

    public LocalDate getCalimDate() { return calimDate; }
    public void setCalimDate(LocalDate calimDate) { this.calimDate = calimDate; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Long getAmount() { return amount; }
    public void setAmount(Long amount) { this.amount = amount; }
}

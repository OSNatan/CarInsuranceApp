package com.example.carins.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "insuranceclaim")
public class InsuranceClaim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate calimDate;

    @NotBlank
    private String description;

    @NotNull
    private Long amount;

    @ManyToOne
    private InsurancePolicy policy;

    public InsuranceClaim() {}

    public InsuranceClaim(LocalDate calimDate, String description, Long amount, InsurancePolicy policy) {
        this.calimDate = calimDate;
        this.description = description;
        this.amount = amount;
        this.policy = policy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCalimDate() { return calimDate; }
    public void setCalimDate(LocalDate calimDate) { this.calimDate = calimDate; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Long getAmount() { return amount; }
    public void setAmount(Long amount) { this.amount = amount; }
}

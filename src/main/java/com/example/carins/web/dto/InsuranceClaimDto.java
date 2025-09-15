package com.example.carins.web.dto;

import java.time.LocalDate;

public class InsuranceClaimDto {
    private LocalDate claimDate;
    private String description;
    private Long amount;

    public InsuranceClaimDto() {}

    public InsuranceClaimDto(LocalDate claimDate, String description, Long amount) {
        this.claimDate = claimDate;
        this.description = description;
        this.amount = amount;
    }

    public LocalDate getClaimDate(){ return this.claimDate; }
    public void setClaimDate(LocalDate claimDate){ this.claimDate = claimDate; }
    public String getDescription() { return this.description; }
    public void setDescription(String description) { this.description = description; }
    public Long getAmount() { return this.amount; }
    public void setAmount(Long amount) { this.amount = amount; }
}

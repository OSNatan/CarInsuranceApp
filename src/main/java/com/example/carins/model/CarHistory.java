package com.example.carins.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "carhistory")
public class CarHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long carId;

    @NotNull
    private Long entityId;

    @NotNull
    @Enumerated(EnumType.STRING)
    private EntityType entityType;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ActionType actionType;

    @NotBlank
    private String info;

    @JsonFormat(pattern = "yyyy_MM-dd")
    private LocalDate timestamp;

    public CarHistory() {}

    public CarHistory(Long carId, Long entityId, EntityType entityType, ActionType actionType, String info) {
        this.carId = carId;
        this.entityId = entityId;
        this.entityType = entityType;
        this.actionType = actionType;
        this.info = info;
        this.timestamp = LocalDate.now();
    }

    public Long getId() { return id; };
    public void setId(Long id) { this.id = id; };
    public Long getCarId() { return carId; };
    public void setCarId(Long carId) { this.carId = carId; };
    public Long getEntityId() { return entityId; };
    public void setEntityId(Long entityId) { this.entityId = entityId; };
    public EntityType getEntityType() { return entityType; };
    public void setEntityType(EntityType entityType) { this.entityType = entityType; };
    public ActionType getActionType() { return actionType; };
    public void setActionType(ActionType actionType) { this.actionType = actionType; };
    public String getInfo() { return info; };
    public void setInfo(String info) { this.info = info; };
    public LocalDate getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDate timestamp) { this.timestamp = timestamp; }
}

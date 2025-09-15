package com.example.carins.web.dto;

import com.example.carins.model.ActionType;
import com.example.carins.model.EntityType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CarHistoryDto {

    @NotNull
    private Long id;

    @NotNull
    private Long carId;

    @NotNull
    private Long entityId;

    @NotBlank
    private EntityType entityType;

    @NotBlank
    private ActionType actionType;

    @NotBlank
    private String info;

    public CarHistoryDto() {}

    public CarHistoryDto(Long id, Long carId, Long entityId, EntityType entityType, ActionType actionType, String info) {
        this.id = id;
        this.carId = carId;
        this.entityId = entityId;
        this.entityType = entityType;
        this.actionType = actionType;
        this.info = info;
    }

    public Long getId() { return id; };
    public Long getEntityId() { return entityId; };
    public Long getCarId() { return carId; };
    public void setCarId(Long carId){ this.carId = carId; };
    public EntityType getEntityType() { return entityType; }
    public ActionType getActionType() { return actionType; }
    public String getInfo() { return info; }
    public void setId(Long id) { this.id = id; }
    public void setEntityId(Long entityId) { this.entityId = entityId; }
    public void setEntityType(EntityType entityType) { this.entityType = entityType; }
    public void setActionType(ActionType actionType) { this.actionType = actionType; }
    public void setInfo(String info) { this.info = info; }
}

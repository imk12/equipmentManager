package com.igor.equipmentManager.controller;

import com.igor.equipmentManager.dto.equipment.*;
import com.igor.equipmentManager.entity.Equipment;
import com.igor.equipmentManager.service.EquipmentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
public class EquipmentController {

    EquipmentService service;

    @PostMapping("equipments")
    public ResponseEntity<EquipmentResponse> registerEquipment(@RequestBody @Valid EquipmentCreation creation){
        return ResponseEntity.status(201).body(service.registerAnEquipment(creation));
    }

    @GetMapping("equipments")
    public ResponseEntity<List<EquipmentResponse>> getEquipments(){
        return ResponseEntity.status(200).body(service.getEquipments());
    }

    @GetMapping("equipments/{Id}")
    public ResponseEntity<EquipmentResponse> getEquipment(
            @PathVariable @PositiveOrZero @NotNull Long Id){
        return ResponseEntity.status(200).body(service.getEquipment(Id));
    }

    @PatchMapping("equipments/{Id}/name")
    public ResponseEntity<Void> updateName(
                    @PathVariable @PositiveOrZero @NotNull Long Id,
                    @RequestBody @Valid EquipmentUpdateName equipmentUpdateName){
        service.UpdateEquipmentName(Id,equipmentUpdateName);
        return ResponseEntity.status(200).build();
    }

    @PatchMapping("equipments/{Id}/assetTag")
    public ResponseEntity<Void> updateAssetTag(
            @PathVariable @PositiveOrZero @NotNull Long Id,
            @RequestBody @Valid EquipmentUpdateAssetTag equipmentUpdateAssetTag){
        service.UpdateEquipmentAssetTag(Id,equipmentUpdateAssetTag);
        return ResponseEntity.status(200).build();
    }

    @PatchMapping("equipments/{Id}/status")
    public ResponseEntity<Void> updateStatus(
            @PathVariable @PositiveOrZero @NotNull Long Id,
            @RequestBody @Valid EquipmentUpdateStatus equipmentUpdateStatus){
        service.UpdateEquipmentStatus(Id,equipmentUpdateStatus);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("equipments/{Id}")
    public ResponseEntity<Void> deleteEquipment(
            @PathVariable @PositiveOrZero @NotNull Long Id){
        service.deleteEquipment(Id);
        return ResponseEntity.ok().build();
    }

    EquipmentController(EquipmentService service){
        this.service = service;
    }

}

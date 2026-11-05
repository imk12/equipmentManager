package com.igor.equipmentManager.entity;

import com.igor.equipmentManager.enums.EquipmentStatus;
import jakarta.persistence.*;

@Entity
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false)
    private String assetTag;

    @Column(nullable = false)
    private String name;

    @Enumerated
    private EquipmentStatus equipmentStatus = EquipmentStatus.AVAILABLE;

    //Getter and Setter

    public Long getId() {
        return id;
    }

    public String getAssetTag() {
        return assetTag;
    }

    public void setAssetTag(String assetTag) {
        this.assetTag = assetTag;
    }

    public EquipmentStatus getEquipmentStatus() {
        return equipmentStatus;
    }

    public void setEquipmentStatus(EquipmentStatus equipmentStatus) {
        this.equipmentStatus = equipmentStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //Constructor

    protected Equipment() {}

    public Equipment(String assetTag, String name){
        this.assetTag = assetTag;
        this.name = name;
    }

}

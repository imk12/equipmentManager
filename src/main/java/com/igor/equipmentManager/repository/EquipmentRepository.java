package com.igor.equipmentManager.repository;

import com.igor.equipmentManager.entity.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
}

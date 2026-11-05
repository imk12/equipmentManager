package com.igor.equipmentManager.service;

import com.igor.equipmentManager.dto.equipment.*;
import com.igor.equipmentManager.entity.Equipment;
import com.igor.equipmentManager.enums.EquipmentStatus;
import com.igor.equipmentManager.exception.AlreadyHasAssetTagException;
import com.igor.equipmentManager.exception.EquipmentNotFoundException;
import com.igor.equipmentManager.repository.EquipmentRepository;
import com.igor.equipmentManager.repository.LoanRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class EquipmentService {
    EquipmentRepository repository;
    LoanRepository loanRepository;

    @Transactional
    //Valida o pedido e registra o equipamento
    public EquipmentResponse registerAnEquipment(EquipmentCreation equipmentCreation){
        //Verifica se já existe o assetTag
        if (repository.existsByAssetTag(equipmentCreation.assetTag())){
            throw new AlreadyHasAssetTagException(equipmentCreation.assetTag());
        }
        Equipment equipment = toEquipment(equipmentCreation);

        Equipment saved = repository.save(equipment);

        return toResponse(saved);
    }

    //Retorna todos os equipments
    public List<EquipmentResponse> getEquipments(){
        //Transforma o Equipment em Response
        List<EquipmentResponse> responses = repository.findAll().stream()
                .map(e -> new EquipmentResponse(e.getId(), e.getName(), e.getAssetTag()))
                .toList();

        return responses;
    }

    //Retorna o equipamento específico
    public EquipmentResponse getEquipment(Long id){
        //Obtém o equipamento e o retorna
        Equipment equipment = repository.findById(id)
                .orElseThrow(() -> new EquipmentNotFoundException("Id",id));

        return toResponse(equipment);
    }

    @Transactional
    //Altera o status
    public boolean UpdateEquipmentStatus(Long id, EquipmentUpdateStatus equipmentUpdateStatus){
        Equipment equipment = repository.findById(id)
                .orElseThrow(() -> new EquipmentNotFoundException("Id", id));

        if (!(equipment.getEquipmentStatus() != EquipmentStatus.DISCARDED)){
            return false;
        }

        equipment.setEquipmentStatus(equipmentUpdateStatus.equipmentStatus());
        return true;
    }

    @Transactional
    //Altera o tag
    public void UpdateEquipmentAssetTag(Long id, EquipmentUpdateAssetTag equipmentUpdateAssetTag){
        Equipment equipment = repository.findById(id)
                .orElseThrow(() -> new EquipmentNotFoundException("Id", id));

        equipment.setAssetTag(equipmentUpdateAssetTag.assetTag());
    }

    @Transactional
    //Altera o name
    public void UpdateEquipmentName(Long id, EquipmentUpdateName equipmentUpdateName){
        Equipment equipment = repository.findById(id)
                .orElseThrow(() -> new EquipmentNotFoundException("Id", id));

        equipment.setAssetTag(equipmentUpdateName.name());
    }

    @Transactional
    //Deleta o equipamento
    public void deleteEquipment(Long id){
        Equipment equipment = repository.findById(id)
                .orElseThrow(() -> new EquipmentNotFoundException("Id",id));

        boolean hasLoan = loanRepository.existsByEquipmentId(id);

        if (hasLoan){
            equipment.setEquipmentStatus(EquipmentStatus.DISCARDED);
            return;
        }

        repository.delete(equipment);
    }

    private Equipment toEquipment(EquipmentCreation equipmentCreation){
        return new Equipment(equipmentCreation.assetTag(), equipmentCreation.name());
    }

    private EquipmentResponse toResponse(Equipment equipment){
        return new EquipmentResponse(equipment.getId(), equipment.getName(), equipment.getAssetTag());
    }

    EquipmentService(
            EquipmentRepository repository,
            LoanRepository loanRepository){
        this.repository = repository;
        this.loanRepository = loanRepository;
    }
}

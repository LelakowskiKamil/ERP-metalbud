package com.lelakowski.ERPMetalbud.mi.service;

import com.lelakowski.ERPMetalbud.mi.converter.MaterialConverter;
import com.lelakowski.ERPMetalbud.mi.domain.model.Material;
import com.lelakowski.ERPMetalbud.mi.domain.repository.MaterialRepository;
import com.lelakowski.ERPMetalbud.mi.web.command.CreateMaterialCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MaterialServiceImpl implements MaterialService{

    private final MaterialConverter materialConverter;
    private final MaterialRepository materialRepository;

    @Override
    public Material saveMaterial(CreateMaterialCommand createMaterialCommand) {
        Material materialToSave = materialConverter.from(createMaterialCommand);
        return materialRepository.save(materialToSave);
    }

    public List<Material> getMaterials(){
        return materialRepository.findAll();
    }
}

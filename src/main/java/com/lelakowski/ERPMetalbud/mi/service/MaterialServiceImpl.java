package com.lelakowski.ERPMetalbud.mi.service;

import com.lelakowski.ERPMetalbud.mi.builder.MaterialBuilder;
import com.lelakowski.ERPMetalbud.mi.domain.model.Material;
import com.lelakowski.ERPMetalbud.mi.domain.repository.MaterialRepository;
import com.lelakowski.ERPMetalbud.mi.web.command.CreateMaterialCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MaterialServiceImpl implements MaterialService {

    private final MaterialBuilder materialBuilder;
    private final MaterialRepository materialRepository;

    @Override
    public Long saveMaterial(CreateMaterialCommand createMaterialCommand) {
        Material materialToSave = materialBuilder.from(createMaterialCommand.getCaption());
        Material material = materialRepository.save(materialToSave);
        return material.getId();
    }

    public List<Material> getMaterials() {
        return materialRepository.findAll();
    }
}

package com.lelakowski.ERPMetalbud.common.domain.dimension.service;

import com.lelakowski.ERPMetalbud.common.domain.dimension.converter.DimensionConverter;
import com.lelakowski.ERPMetalbud.common.domain.dimension.domain.repository.DimensionRepository;
import com.lelakowski.ERPMetalbud.common.web.command.CreateDimensionsCommand;
import com.lelakowski.ERPMetalbud.mi.converter.BillOfMaterialItemConverter;
import com.lelakowski.ERPMetalbud.mi.domain.repository.BillOfMaterialItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.lelakowski.ERPMetalbud.common.domain.dimension.domain.model.Dimension;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DimensionServiceImpl implements DimensionService {

    private final DimensionConverter dimensionConverter;
    private final DimensionRepository dimensionRepository;


    @Override
    @Transactional
    public Dimension saveDimension(Double value, String unit) { //TODO should return dimensionId
        Dimension dimensionToSave = dimensionConverter.from(value,unit);
        return dimensionRepository.save(dimensionToSave);
    }

    @Override
    public List<Dimension> geDimensions() {
        return dimensionRepository.findAll();
    }
}

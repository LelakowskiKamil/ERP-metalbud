package com.lelakowski.ERPMetalbud.common.domain.dimension.service;

import com.lelakowski.ERPMetalbud.common.domain.dimension.converter.DimensionConverter;
import com.lelakowski.ERPMetalbud.common.domain.dimension.converter.DimensionsConverter;
import com.lelakowski.ERPMetalbud.common.domain.dimension.domain.model.Dimensions;
import com.lelakowski.ERPMetalbud.common.domain.dimension.domain.repository.DimensionsRepository;
import com.lelakowski.ERPMetalbud.common.web.command.CreateDimensionsCommand;
import com.lelakowski.ERPMetalbud.mi.converter.BillOfMaterialItemConverter;
import com.lelakowski.ERPMetalbud.mi.domain.model.BillOfMaterialItem;
import com.lelakowski.ERPMetalbud.mi.domain.repository.BillOfMaterialItemRepository;
import com.lelakowski.ERPMetalbud.mi.service.BillOfMaterialItemService;
import com.lelakowski.ERPMetalbud.mi.web.command.CreateBillOfMaterialItemCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DimensionsServiceImpl implements DimensionsService {

    private final DimensionsConverter dimensionsConverter;
    private final DimensionsRepository dimensionsRepository;
    private final DimensionConverter dimensionConverter;
    private final DimensionService dimensionService;


    @Override
    @Transactional
    public Dimensions saveDimensions(CreateDimensionsCommand createDimensionsCommand) {
        Dimensions dimensionsToSave = dimensionsConverter.from(createDimensionsCommand);
        return dimensionsRepository.save(dimensionsToSave);
    }

    @Override
    public List<Dimensions> geDimensions() {
        return dimensionsRepository.findAll();
    }
}

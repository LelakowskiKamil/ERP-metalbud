package com.lelakowski.ERPMetalbud.common.domain.dimension.converter;

import com.lelakowski.ERPMetalbud.common.domain.dimension.domain.model.Dimension;
import com.lelakowski.ERPMetalbud.common.domain.dimension.domain.model.Dimensions;
import com.lelakowski.ERPMetalbud.common.domain.dimension.domain.repository.DimensionsRepository;
import com.lelakowski.ERPMetalbud.common.domain.dimension.service.DimensionService;
import com.lelakowski.ERPMetalbud.common.web.command.CreateDimensionsCommand;
import com.lelakowski.ERPMetalbud.mi.domain.model.BillOfMaterialItem;
import com.lelakowski.ERPMetalbud.mi.domain.model.Material;
import com.lelakowski.ERPMetalbud.mi.domain.repository.BillOfMaterialsRepository;
import com.lelakowski.ERPMetalbud.mi.domain.repository.MaterialRepository;
import com.lelakowski.ERPMetalbud.mi.web.command.CreateBillOfMaterialItemCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DimensionsConverter {

    private final DimensionService dimensionService;

    public Dimensions from(CreateDimensionsCommand createDimensionsCommand) {
        if (createDimensionsCommand == null) throw new IllegalArgumentException();

        Dimension height = dimensionService.saveDimension(
                createDimensionsCommand.getHeight(),
                createDimensionsCommand.getUnit()
        );
        Dimension length = dimensionService.saveDimension(
                createDimensionsCommand.getLength(),
                createDimensionsCommand.getUnit()
        );
        Dimension width = dimensionService.saveDimension(
                createDimensionsCommand.getWidth(),
                createDimensionsCommand.getUnit()
        );

        return Dimensions.builder()
                .caption(createDimensionsCommand.getCaption())
                .height(height)
                .length(length)
                .width(width)
                .build();
    }

}

package com.lelakowski.ERPMetalbud.common.domain.dimension.converter;

import com.lelakowski.ERPMetalbud.common.domain.dimension.domain.model.Dimension;
import com.lelakowski.ERPMetalbud.common.domain.dimension.domain.repository.DimensionRepository;
import com.lelakowski.ERPMetalbud.mi.domain.model.BillOfMaterialItem;
import com.lelakowski.ERPMetalbud.mi.domain.model.Material;
import com.lelakowski.ERPMetalbud.mi.domain.repository.BillOfMaterialsRepository;
import com.lelakowski.ERPMetalbud.mi.web.command.CreateBillOfMaterialItemCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DimensionConverter {

    public Dimension from(Double value, String unit) {
        if (value == null || unit.equals("")) throw new IllegalArgumentException();

        //TODO validation does the material with the given id exist
        return Dimension.builder()
                .value(value)
                .unit(unit)
                .build();
    }

}

package com.lelakowski.ERPMetalbud.mi.builder;

import com.lelakowski.ERPMetalbud.mi.domain.model.BillOfMaterialItem;
import com.lelakowski.ERPMetalbud.mi.domain.model.Material;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BillOfMaterialItemBuilder {

    public BillOfMaterialItem from(Material material, Double quantity) {
        return BillOfMaterialItem.builder()
                .material(material)
                .quantity(quantity)
                .build();
    }

}

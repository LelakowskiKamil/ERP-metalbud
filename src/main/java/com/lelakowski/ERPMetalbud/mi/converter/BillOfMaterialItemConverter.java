package com.lelakowski.ERPMetalbud.mi.converter;

import com.lelakowski.ERPMetalbud.mi.domain.model.BillOfMaterialItem;
import com.lelakowski.ERPMetalbud.mi.domain.model.BillOfMaterials;
import com.lelakowski.ERPMetalbud.mi.domain.model.Material;
import com.lelakowski.ERPMetalbud.mi.domain.repository.BillOfMaterialsRepository;
import com.lelakowski.ERPMetalbud.mi.domain.repository.MaterialRepository;
import com.lelakowski.ERPMetalbud.mi.web.command.CreateBillOfMaterialItemCommand;
import com.lelakowski.ERPMetalbud.mi.web.command.CreateBillOfMaterialsCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BillOfMaterialItemConverter {

    private final MaterialRepository materialRepository;
    private final BillOfMaterialsRepository billOfMaterialsRepository;

    public BillOfMaterialItem from(CreateBillOfMaterialItemCommand createBillOfMaterialsCommand) {
        if (createBillOfMaterialsCommand == null) throw new IllegalArgumentException();

        //TODO validation does the material with the given id exist
        Material materialFromCommand = materialRepository.getOne(createBillOfMaterialsCommand.getMaterialId());

        return BillOfMaterialItem.builder()
                .material(materialFromCommand)
                .quantity(createBillOfMaterialsCommand.getQuantity())
                .build();
    }

}

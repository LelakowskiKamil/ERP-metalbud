package com.lelakowski.ERPMetalbud.mi.converter;

import com.lelakowski.ERPMetalbud.mi.domain.model.BillOfMaterialItem;
import com.lelakowski.ERPMetalbud.mi.domain.model.BillOfMaterials;
import com.lelakowski.ERPMetalbud.mi.web.command.CreateBillOfMaterialItemCommand;
import com.lelakowski.ERPMetalbud.mi.web.command.CreateBillOfMaterialsCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BillOfMaterialsConverter {

    private final BillOfMaterialItemConverter billOfMaterialItemConverter;

    public BillOfMaterials from(CreateBillOfMaterialsCommand createBillOfMaterialsCommand) {
        if (createBillOfMaterialsCommand == null) throw new IllegalArgumentException();

        List<CreateBillOfMaterialItemCommand> createBillOfMaterialItemCommands =
                createBillOfMaterialsCommand.getBillOfMaterialItems();
        List<BillOfMaterialItem> billOfMaterialItems = createBillOfMaterialItemCommands
                .stream()
                .map(billOfMaterialItemConverter::from)
                .collect(Collectors.toList());

        return BillOfMaterials.builder()
            //    .billOfMaterialItems(billOfMaterialItems)
                .build();
    }


}

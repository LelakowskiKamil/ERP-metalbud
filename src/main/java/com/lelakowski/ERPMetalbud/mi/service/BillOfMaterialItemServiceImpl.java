package com.lelakowski.ERPMetalbud.mi.service;

import com.lelakowski.ERPMetalbud.mi.builder.BillOfMaterialItemBuilder;
import com.lelakowski.ERPMetalbud.mi.domain.model.BillOfMaterialItem;
import com.lelakowski.ERPMetalbud.mi.domain.model.Material;
import com.lelakowski.ERPMetalbud.mi.domain.repository.BillOfMaterialItemRepository;
import com.lelakowski.ERPMetalbud.mi.domain.repository.MaterialRepository;
import com.lelakowski.ERPMetalbud.mi.validation.CreateBillOfMaterialItemValidator;
import com.lelakowski.ERPMetalbud.mi.web.command.CreateBillOfMaterialItemCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BillOfMaterialItemServiceImpl implements BillOfMaterialItemService {

    private final BillOfMaterialItemBuilder billOfMaterialItemBuilder;
    private final BillOfMaterialItemRepository billOfMaterialItemRepository;
    private final MaterialRepository materialRepository;
    private final CreateBillOfMaterialItemValidator createBillOfMaterialItemValidator;

    @Transactional
    @Override
    public Long saveBillOfMaterialItem(CreateBillOfMaterialItemCommand createBillOfMaterialItemCommand) {
        createBillOfMaterialItemValidator.validate(createBillOfMaterialItemCommand);
        Material material = materialRepository.getOne(createBillOfMaterialItemCommand.getMaterialId());

        BillOfMaterialItem billOfMaterialItemToSave = billOfMaterialItemBuilder.from(
                material,
                createBillOfMaterialItemCommand.getQuantity()
        );
        BillOfMaterialItem billOfMaterialItem = billOfMaterialItemRepository.save(billOfMaterialItemToSave);
        saveReferences(billOfMaterialItem, material);

        return billOfMaterialItem.getId();
    }

    @Override
    public List<BillOfMaterialItem> getBillOfMaterialItems() {
        return billOfMaterialItemRepository.findAll();
    }

    private void saveReferences(BillOfMaterialItem billOfMaterialItem, Material material) {
        material.addToBillOfMaterialItemList(billOfMaterialItem);
    }

}

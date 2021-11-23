package com.lelakowski.ERPMetalbud.mi.service;

import com.lelakowski.ERPMetalbud.mi.converter.BillOfMaterialItemConverter;
import com.lelakowski.ERPMetalbud.mi.converter.BillOfMaterialsConverter;
import com.lelakowski.ERPMetalbud.mi.domain.model.BillOfMaterialItem;
import com.lelakowski.ERPMetalbud.mi.domain.model.BillOfMaterials;
import com.lelakowski.ERPMetalbud.mi.domain.repository.BillOfMaterialItemRepository;
import com.lelakowski.ERPMetalbud.mi.domain.repository.BillOfMaterialsRepository;
import com.lelakowski.ERPMetalbud.mi.web.command.CreateBillOfMaterialItemCommand;
import com.lelakowski.ERPMetalbud.mi.web.command.CreateBillOfMaterialsCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BillOfMaterialItemServiceImpl implements BillOfMaterialItemService {

    private final BillOfMaterialItemConverter billOfMaterialItemConverter;
    private final BillOfMaterialItemRepository billOfMaterialItemRepository;


    @Override
    public BillOfMaterialItem saveBillOfMaterialItem(CreateBillOfMaterialItemCommand createBillOfMaterialItemCommand) {
        BillOfMaterialItem billOfMaterialItemToSave = billOfMaterialItemConverter.from(createBillOfMaterialItemCommand);
        return billOfMaterialItemRepository.save(billOfMaterialItemToSave);
    }

    @Override
    public List<BillOfMaterialItem> getBillOfMaterialItems() {
        return billOfMaterialItemRepository.findAll();
    }
}

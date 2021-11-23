package com.lelakowski.ERPMetalbud.mi.service;

import com.lelakowski.ERPMetalbud.mi.converter.BillOfMaterialsConverter;
import com.lelakowski.ERPMetalbud.mi.converter.MaterialConverter;
import com.lelakowski.ERPMetalbud.mi.domain.model.BillOfMaterials;
import com.lelakowski.ERPMetalbud.mi.domain.repository.BillOfMaterialsRepository;
import com.lelakowski.ERPMetalbud.mi.domain.repository.MaterialRepository;
import com.lelakowski.ERPMetalbud.mi.web.command.CreateBillOfMaterialsCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BillOfMaterialsServiceImpl implements BillOfMaterialsService {

    private final BillOfMaterialsConverter billOfMaterialsConverter;
    private final BillOfMaterialsRepository billOfMaterialsRepository;

    @Override
    public BillOfMaterials saveBillOfMaterial(CreateBillOfMaterialsCommand createBillOfMaterialsCommand) {
        return billOfMaterialsConverter.from(createBillOfMaterialsCommand);
    }

    @Override
    public List<BillOfMaterials> getBillOfMaterials() {
        return billOfMaterialsRepository.findAll();
    }
}

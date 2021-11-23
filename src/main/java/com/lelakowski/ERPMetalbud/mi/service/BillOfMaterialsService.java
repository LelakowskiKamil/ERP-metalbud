package com.lelakowski.ERPMetalbud.mi.service;

import com.lelakowski.ERPMetalbud.mi.domain.model.BillOfMaterials;
import com.lelakowski.ERPMetalbud.mi.domain.model.Material;
import com.lelakowski.ERPMetalbud.mi.web.command.CreateBillOfMaterialsCommand;
import com.lelakowski.ERPMetalbud.mi.web.command.CreateMaterialCommand;

import java.util.List;

public interface BillOfMaterialsService {

    BillOfMaterials saveBillOfMaterial(CreateBillOfMaterialsCommand createBillOfMaterialsCommand);

    List<BillOfMaterials> getBillOfMaterials();

}

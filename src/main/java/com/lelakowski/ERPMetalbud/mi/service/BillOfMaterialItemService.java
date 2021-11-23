package com.lelakowski.ERPMetalbud.mi.service;

import com.lelakowski.ERPMetalbud.mi.domain.model.BillOfMaterialItem;
import com.lelakowski.ERPMetalbud.mi.domain.model.BillOfMaterials;
import com.lelakowski.ERPMetalbud.mi.web.command.CreateBillOfMaterialItemCommand;
import com.lelakowski.ERPMetalbud.mi.web.command.CreateBillOfMaterialsCommand;

import java.util.List;

public interface BillOfMaterialItemService {

    BillOfMaterialItem saveBillOfMaterialItem(CreateBillOfMaterialItemCommand createBillOfMaterialItemCommand);

    List<BillOfMaterialItem> getBillOfMaterialItems();

}

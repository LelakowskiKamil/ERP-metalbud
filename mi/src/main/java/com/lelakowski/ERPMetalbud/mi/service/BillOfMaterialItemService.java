package com.lelakowski.ERPMetalbud.mi.service;

import com.lelakowski.ERPMetalbud.mi.domain.model.BillOfMaterialItem;
import com.lelakowski.ERPMetalbud.mi.web.command.CreateBillOfMaterialItemCommand;

import java.util.List;

public interface BillOfMaterialItemService {

    Long saveBillOfMaterialItem(CreateBillOfMaterialItemCommand createBillOfMaterialItemCommand);

    List<BillOfMaterialItem> getBillOfMaterialItems();

}

package com.lelakowski.ERPMetalbud.common.domain.dimension.service;

import com.lelakowski.ERPMetalbud.common.domain.dimension.domain.model.Dimensions;
import com.lelakowski.ERPMetalbud.common.web.command.CreateDimensionsCommand;
import com.lelakowski.ERPMetalbud.mi.domain.model.BillOfMaterialItem;
import com.lelakowski.ERPMetalbud.mi.web.command.CreateBillOfMaterialItemCommand;

import java.util.List;

public interface DimensionsService {

    Dimensions saveDimensions(CreateDimensionsCommand createDimensionsCommand);

    List<Dimensions> geDimensions();

}

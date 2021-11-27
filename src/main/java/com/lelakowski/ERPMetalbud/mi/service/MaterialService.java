package com.lelakowski.ERPMetalbud.mi.service;

import com.lelakowski.ERPMetalbud.mi.domain.model.Material;
import com.lelakowski.ERPMetalbud.mi.web.command.CreateMaterialCommand;
import com.lelakowski.ERPMetalbud.om.domain.model.ProductOrderItem;
import com.lelakowski.ERPMetalbud.om.web.command.CreateOrderItemCommand;

import java.util.List;

public interface MaterialService {

    Long saveMaterial(CreateMaterialCommand createMaterialCommand);

    List<Material> getMaterials();

}

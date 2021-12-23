package com.lelakowski.ERPMetalbud.pi.service;

import com.lelakowski.ERPMetalbud.pi.domain.model.Dimensions;
import com.lelakowski.ERPMetalbud.pi.web.command.CreateDimensionsCommand;

import java.util.List;

public interface DimensionsService {

    Long saveDimensions(CreateDimensionsCommand createDimensionsCommand);

    List<Dimensions> getDimensions();

    Long getDimensionsIdByCaption(String externalName);

}

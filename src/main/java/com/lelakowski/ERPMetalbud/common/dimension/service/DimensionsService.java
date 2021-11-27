package com.lelakowski.ERPMetalbud.common.dimension.service;

import com.lelakowski.ERPMetalbud.common.dimension.domain.model.Dimensions;
import com.lelakowski.ERPMetalbud.common.web.command.CreateDimensionsCommand;

import java.util.List;

public interface DimensionsService {

    Long saveDimensions(CreateDimensionsCommand createDimensionsCommand);

    List<Dimensions> geDimensions();

}

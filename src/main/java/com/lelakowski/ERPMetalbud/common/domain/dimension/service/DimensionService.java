package com.lelakowski.ERPMetalbud.common.domain.dimension.service;

import com.lelakowski.ERPMetalbud.common.web.command.CreateDimensionsCommand;

import java.awt.*;
import java.util.List;
import com.lelakowski.ERPMetalbud.common.domain.dimension.domain.model.Dimension;

public interface DimensionService {

    Dimension saveDimension(Double value, String unit);

    List<Dimension> geDimensions();

}

package com.lelakowski.ERPMetalbud.common.dimension.service;

import com.lelakowski.ERPMetalbud.common.dimension.domain.model.Dimension;

import java.util.List;

public interface DimensionService {

    Dimension saveDimension(Double value, String unit);

    List<Dimension> geDimensions();

}

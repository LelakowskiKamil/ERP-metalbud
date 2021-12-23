package com.lelakowski.ERPMetalbud.pi.service;


import com.lelakowski.ERPMetalbud.pi.domain.model.Dimension;

import java.util.List;

public interface DimensionService {

    Dimension saveDimension(Double value, String unit);

    List<Dimension> getDimensions();

}

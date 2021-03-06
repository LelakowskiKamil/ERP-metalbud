package com.lelakowski.ERPMetalbud.pi.service;

import com.lelakowski.ERPMetalbud.pi.builder.DimensionBuilder;
import com.lelakowski.ERPMetalbud.pi.domain.model.Dimension;
import com.lelakowski.ERPMetalbud.pi.domain.repository.DimensionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DimensionServiceImpl implements DimensionService {

    private final DimensionBuilder dimensionBuilder;
    private final DimensionRepository dimensionRepository;

    @Override
    public Dimension saveDimension(Double value, String unit) {
        Dimension dimensionToSave = dimensionBuilder.from(value, unit);
        return dimensionRepository.save(dimensionToSave);
    }

    @Override
    public List<Dimension> getDimensions() {
        return dimensionRepository.findAll();
    }
}

package com.lelakowski.ERPMetalbud.common.dimension.service;

import com.lelakowski.ERPMetalbud.common.dimension.builder.DimensionsBuilder;
import com.lelakowski.ERPMetalbud.common.dimension.domain.model.Dimension;
import com.lelakowski.ERPMetalbud.common.dimension.domain.model.Dimensions;
import com.lelakowski.ERPMetalbud.common.dimension.domain.repository.DimensionsRepository;
import com.lelakowski.ERPMetalbud.common.dimension.web.command.CreateDimensionsCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DimensionsServiceImpl implements DimensionsService {

    private final DimensionsBuilder dimensionsBuilder;
    private final DimensionsRepository dimensionsRepository;
    private final DimensionService dimensionService;

    @Transactional
    @Override
    public Long saveDimensions(CreateDimensionsCommand createDimensionsCommand) {
        Dimension height = dimensionService.saveDimension(
                createDimensionsCommand.getHeight(),
                createDimensionsCommand.getUnit()
        );

        Dimension width = dimensionService.saveDimension(
                createDimensionsCommand.getWidth(),
                createDimensionsCommand.getUnit()
        );

        Dimension length = dimensionService.saveDimension(
                createDimensionsCommand.getLength(),
                createDimensionsCommand.getUnit()
        );


        Dimensions dimensionsToSave = dimensionsBuilder.from(
                createDimensionsCommand.getCaption(),
                height,
                width,
                length
        );

        Dimensions dimensions = dimensionsRepository.save(dimensionsToSave);
        saveReferences(dimensions, height, width, length);

        return dimensions.getId();
    }

    @Override
    public List<Dimensions> getDimensions() {
        return dimensionsRepository.findAll();
    }

    private void saveReferences(
            Dimensions dimensionsReference,
            Dimension height,
            Dimension width,
            Dimension length
    ) {
        height.addToDimensionsHeightList(dimensionsReference);
        width.addToDimensionsWidthList(dimensionsReference);
        length.addToDimensionsLengthList(dimensionsReference);
    }
}

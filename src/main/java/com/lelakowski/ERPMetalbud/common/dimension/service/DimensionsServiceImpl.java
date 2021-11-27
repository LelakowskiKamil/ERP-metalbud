package com.lelakowski.ERPMetalbud.common.dimension.service;

import com.lelakowski.ERPMetalbud.common.dimension.builder.DimensionsBuilder;
import com.lelakowski.ERPMetalbud.common.dimension.domain.model.Dimension;
import com.lelakowski.ERPMetalbud.common.dimension.domain.model.Dimensions;
import com.lelakowski.ERPMetalbud.common.dimension.domain.repository.DimensionsRepository;
import com.lelakowski.ERPMetalbud.common.web.command.CreateDimensionsCommand;
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

        Dimension length = dimensionService.saveDimension(
                createDimensionsCommand.getLength(),
                createDimensionsCommand.getUnit()
        );
        Dimension width = dimensionService.saveDimension(
                createDimensionsCommand.getWidth(),
                createDimensionsCommand.getUnit()
        );

        Dimensions dimensionsToSave = dimensionsBuilder.from(
                createDimensionsCommand.getCaption(),
                height,
                length,
                width
        );

        Dimensions dimensions = dimensionsRepository.save(dimensionsToSave);
        saveReferences(dimensions,height,length,width);

        return dimensions.getId();
    }

    @Override
    public List<Dimensions> geDimensions() {
        return dimensionsRepository.findAll();
    }

    private void saveReferences(
            Dimensions dimensionsReference,
            Dimension height,
            Dimension length,
            Dimension width
    ) {
        System.out.println(height.toString());
        System.out.println(length.toString());
        System.out.println(width.toString());
        System.out.println(dimensionsReference.toString());
        height.addToDimensionsHeightList(dimensionsReference);
        length.addToDimensionsLengthList(dimensionsReference);
        width.addToDimensionsWidthList(dimensionsReference);
    }
}

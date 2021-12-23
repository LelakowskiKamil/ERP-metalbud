package com.lelakowski.ERPMetalbud.pi.service;

import com.lelakowski.ERPMetalbud.pi.builder.DimensionsBuilder;
import com.lelakowski.ERPMetalbud.pi.domain.model.Dimension;
import com.lelakowski.ERPMetalbud.pi.domain.model.Dimensions;
import com.lelakowski.ERPMetalbud.pi.domain.repository.DimensionsRepository;
import com.lelakowski.ERPMetalbud.pi.notification.NotFoundDimensionsWithCaptionException;
import com.lelakowski.ERPMetalbud.pi.web.command.CreateDimensionsCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
        return dimensions.getId();
    }

    @Override
    public List<Dimensions> getDimensions() {
        return dimensionsRepository.findAll();
    }

    @Override
    public Long getDimensionsIdByCaption(String caption) {
        Optional<Long> dimensionsIdOpt = dimensionsRepository.findDimensionByCaptionName(caption);
        if (dimensionsIdOpt.isEmpty()) throw new NotFoundDimensionsWithCaptionException(caption);
        return dimensionsIdOpt.get();
    }

}

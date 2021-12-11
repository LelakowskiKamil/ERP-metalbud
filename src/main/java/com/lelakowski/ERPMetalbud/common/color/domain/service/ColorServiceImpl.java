package com.lelakowski.ERPMetalbud.common.color.domain.service;

import com.lelakowski.ERPMetalbud.common.color.domain.builder.ColorBuilder;
import com.lelakowski.ERPMetalbud.common.color.domain.model.Color;
import com.lelakowski.ERPMetalbud.common.color.domain.repository.ColorRepository;
import com.lelakowski.ERPMetalbud.common.color.web.command.CreateColorCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ColorServiceImpl implements ColorService {

    private final ColorBuilder colorBuilder;
    private final ColorRepository colorRepository;

    @Override
    public Color saveColor(CreateColorCommand createColorCommand) {
        Color colorToSave = colorBuilder.from(createColorCommand.getOem(), createColorCommand.getCaption());
        return colorRepository.save(colorToSave);
    }

    @Override
    public List<Color> getColors() {
        return colorRepository.findAll();
    }
}

package com.lelakowski.ERPMetalbud.pi.service;

import com.lelakowski.ERPMetalbud.pi.builder.ColorBuilder;
import com.lelakowski.ERPMetalbud.pi.domain.model.Color;
import com.lelakowski.ERPMetalbud.pi.domain.repository.ColorRepository;
import com.lelakowski.ERPMetalbud.pi.notification.NotFoundColorWithExternalNameException;
import com.lelakowski.ERPMetalbud.pi.notification.NotFoundColorWithOEMException;
import com.lelakowski.ERPMetalbud.pi.web.command.CreateColorCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Long getColorIdByExternalName(String externalName) {
        Optional<Long> colorIdOpt = colorRepository.findColorByExternalName(externalName);
        if (colorIdOpt.isEmpty()) throw new NotFoundColorWithExternalNameException(externalName);
        return colorIdOpt.get();
    }

    @Override
    public Long getColorIdByOem(String oem) {
        Optional<Long> colorIdOpt = colorRepository.findColorByOEM(oem);
        if (colorIdOpt.isEmpty()) throw new NotFoundColorWithOEMException(oem);
        return colorIdOpt.get();
    }
}

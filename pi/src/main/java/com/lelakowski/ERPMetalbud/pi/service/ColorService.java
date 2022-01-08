package com.lelakowski.ERPMetalbud.pi.service;

import com.lelakowski.ERPMetalbud.pi.domain.model.Color;
import com.lelakowski.ERPMetalbud.pi.web.command.CreateColorCommand;

import java.util.List;

public interface ColorService {

    Color saveColor(CreateColorCommand createColorCommand);

    List<Color> getColors();

    Long getColorIdByExternalName(String externalName);

    Long getColorIdByOem(String oem);


}

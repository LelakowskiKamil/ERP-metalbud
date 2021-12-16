package com.lelakowski.ERPMetalbud.common.color.service;

import com.lelakowski.ERPMetalbud.common.color.domain.model.Color;
import com.lelakowski.ERPMetalbud.common.color.web.command.CreateColorCommand;

import java.util.List;

public interface ColorService {

    Color saveColor(CreateColorCommand createColorCommand);

    List<Color> getColors();

}

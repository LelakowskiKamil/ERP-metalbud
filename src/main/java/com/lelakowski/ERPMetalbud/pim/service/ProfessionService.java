package com.lelakowski.ERPMetalbud.pim.service;

import com.lelakowski.ERPMetalbud.pim.domain.model.Profession;
import com.lelakowski.ERPMetalbud.pim.web.command.CreateProfessionCommand;

import java.util.List;

public interface ProfessionService {

    Long saveProfession(CreateProfessionCommand createProfessionCommand);

    List<Profession> getProfessions();
}

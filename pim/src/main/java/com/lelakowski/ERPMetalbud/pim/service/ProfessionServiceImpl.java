package com.lelakowski.ERPMetalbud.pim.service;

import com.lelakowski.ERPMetalbud.pim.builder.ProfessionBuilder;
import com.lelakowski.ERPMetalbud.pim.domain.model.Profession;
import com.lelakowski.ERPMetalbud.pim.domain.repository.ProfessionRepository;
import com.lelakowski.ERPMetalbud.pim.web.command.CreateProfessionCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfessionServiceImpl implements ProfessionService {

    private final ProfessionRepository professionRepository;
    private final ProfessionBuilder professionBuilder;

    @Override
    public Long saveProfession(CreateProfessionCommand createProfessionCommand) {
        Profession professionToSave = professionBuilder.from(
                createProfessionCommand.getDesignation()
        );

        Profession profession = professionRepository.save(professionToSave);

        return profession.getId();
    }

    @Override
    public List<Profession> getProfessions() {
        return professionRepository.findAll();
    }


}

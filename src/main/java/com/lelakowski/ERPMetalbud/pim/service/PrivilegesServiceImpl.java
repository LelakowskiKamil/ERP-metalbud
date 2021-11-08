package com.lelakowski.ERPMetalbud.pim.service;

import com.lelakowski.ERPMetalbud.pim.converter.PrivilegesConverter;
import com.lelakowski.ERPMetalbud.pim.domain.model.Privileges;
import com.lelakowski.ERPMetalbud.pim.domain.repository.PrivilegesRepository;
import com.lelakowski.ERPMetalbud.pim.web.command.CreatePrivilegesCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PrivilegesServiceImpl implements PrivilegesService {

    private final PrivilegesRepository privilegesRepository;

    public Privileges savePrivileges(CreatePrivilegesCommand privilegesCommand) {
        Privileges privilegesToSave = new PrivilegesConverter().from(privilegesCommand);
        return privilegesRepository.save(privilegesToSave);
    }

    public List<Privileges> getPrivileges() {
     return privilegesRepository.findAll();
    }


}

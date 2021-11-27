package com.lelakowski.ERPMetalbud.pim.service;

import com.lelakowski.ERPMetalbud.pim.builder.PrivilegesBuilder;
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
    private final PrivilegesBuilder privilegesBuilder;

    @Override
    public Long savePrivileges(CreatePrivilegesCommand privilegesCommand) {
        Privileges privilegesToSave = privilegesBuilder.from(
                privilegesCommand.getCaption(),
                privilegesCommand.getCanView(),
                privilegesCommand.getCanCreate(),
                privilegesCommand.getCanUpdate(),
                privilegesCommand.getCanRemove()
        );

        Privileges privileges = privilegesRepository.save(privilegesToSave);

        return privileges.getId();
    }

    public List<Privileges> getPrivileges() {
     return privilegesRepository.findAll();
    }


}

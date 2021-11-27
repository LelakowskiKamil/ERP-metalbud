package com.lelakowski.ERPMetalbud.pim.service;

import com.lelakowski.ERPMetalbud.pim.domain.model.Privileges;
import com.lelakowski.ERPMetalbud.pim.web.command.CreatePrivilegesCommand;

import java.util.List;

public interface PrivilegesService {

    Long savePrivileges(CreatePrivilegesCommand privilegesCommand);

    List<Privileges> getPrivileges();
}

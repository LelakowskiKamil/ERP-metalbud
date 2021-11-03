package com.lelakowski.ERPMetalbud.pim.service;

import com.lelakowski.ERPMetalbud.pim.domain.model.Privileges;
import com.lelakowski.ERPMetalbud.pim.repository.PrivilegesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrivilegesService {

    PrivilegesRepository privilegesRepository;

    public PrivilegesService(PrivilegesRepository privilegesRepository) {
        this.privilegesRepository = privilegesRepository;
    }

    public Privileges savePrivileges(Privileges privileges) {
        return privilegesRepository.save(privileges);
    }

    public List<Privileges> getPrivileges() {
     return privilegesRepository.findAll();
    }



}

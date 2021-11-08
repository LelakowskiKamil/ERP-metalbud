package com.lelakowski.ERPMetalbud.pim.converter;

import com.lelakowski.ERPMetalbud.pim.domain.model.Privileges;
import com.lelakowski.ERPMetalbud.pim.web.command.CreatePrivilegesCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class PrivilegesConverter {

    public Privileges from(CreatePrivilegesCommand createPrivilegesCommand) {
        if (createPrivilegesCommand == null) throw new IllegalArgumentException();
        return Privileges.builder()
                .caption(createPrivilegesCommand.getCaption())
                .canView(createPrivilegesCommand.getCanView())
                .canCreate(createPrivilegesCommand.getCanCreate())
                .canUpdate(createPrivilegesCommand.getCanUpdate())
                .canRemove(createPrivilegesCommand.getCanRemove())
                .build();
    }
}

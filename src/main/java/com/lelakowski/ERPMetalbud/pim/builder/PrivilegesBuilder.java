package com.lelakowski.ERPMetalbud.pim.builder;

import com.lelakowski.ERPMetalbud.pim.domain.model.Privileges;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class PrivilegesBuilder {

    public Privileges from(String caption, Boolean view, Boolean create, Boolean update, Boolean remove) {
//TODO validation
        return Privileges.builder()
                .caption(caption)
                .canView(view)
                .canCreate(create)
                .canUpdate(update)
                .canRemove(remove)
                .accounts(new ArrayList<>())
                .build();
    }
}

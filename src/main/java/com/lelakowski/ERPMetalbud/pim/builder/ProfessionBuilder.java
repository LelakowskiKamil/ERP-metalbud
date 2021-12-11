package com.lelakowski.ERPMetalbud.pim.builder;

import com.lelakowski.ERPMetalbud.pim.domain.model.Profession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class ProfessionBuilder {

    public Profession from(String designation) {
        return Profession.builder()
                .designation(designation)
                .employees(new ArrayList<>())
                .build();
    }
}

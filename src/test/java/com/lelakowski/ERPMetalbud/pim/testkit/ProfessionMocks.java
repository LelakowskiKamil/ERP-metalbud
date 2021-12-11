package com.lelakowski.ERPMetalbud.pim.testkit;

import com.lelakowski.ERPMetalbud.pim.domain.model.Profession;

import java.util.Collections;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProfessionMocks {

    public static Profession profession1 = mockProfession(1L, "Designation1");
    public static Profession profession2 = mockProfession(2L, "Designation2");
    public static Profession profession3 = mockProfession(3L, "Designation3");
    public static Profession profession4 = mockProfession(4L, "Designation4");

    public static Profession mockProfession(Long id, String designation) {
        Profession profession = mock(Profession.class);
        when(profession.getId()).thenReturn(id);
        when(profession.getDesignation()).thenReturn(designation);
        when(profession.getEmployees()).thenReturn(Collections.emptyList());
        return profession;
    }
}

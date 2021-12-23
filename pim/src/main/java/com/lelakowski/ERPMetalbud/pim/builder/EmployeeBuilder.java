package com.lelakowski.ERPMetalbud.pim.builder;

import com.lelakowski.ERPMetalbud.pim.domain.model.Employee;
import com.lelakowski.ERPMetalbud.pim.domain.model.Profession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeeBuilder {

    public Employee from(String externalName, String email, Profession profession, String employmentDate, Long salaryId) {
        return Employee.builder()
                .externalName(externalName)
                .email(email)
                .profession(profession)
                .employmentDate(employmentDate)
                .salaryId(salaryId)
                .build();
    }

}

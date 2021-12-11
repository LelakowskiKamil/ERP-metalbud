package com.lelakowski.ERPMetalbud.pim.builder;

import com.lelakowski.ERPMetalbud.pe.domain.model.Price;
import com.lelakowski.ERPMetalbud.pim.domain.model.Employee;
import com.lelakowski.ERPMetalbud.pim.domain.model.Profession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeeBuilder {

    public Employee from(String email, Profession profession, String employmentDate, Price salary) {
        return Employee.builder()
                .email(email)
                .profession(profession)
                .employmentDate(employmentDate)
                .salaryGross(salary)
                .build();
    }

}

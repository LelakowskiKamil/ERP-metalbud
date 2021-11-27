package com.lelakowski.ERPMetalbud.pim.builder;

import com.lelakowski.ERPMetalbud.pe.domain.model.Price;
import com.lelakowski.ERPMetalbud.pim.domain.model.Employee;
import com.lelakowski.ERPMetalbud.pim.domain.model.Profession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class EmployeeBuilder {

    public Employee from(String email, Profession profession, Date employmentDate, Price salary) {
//TODO validation
        return Employee.builder()
                .email(email)
                .profession(profession)
                .employmentDate(employmentDate)
                .salaryGross(salary)
                .build();
    }

}

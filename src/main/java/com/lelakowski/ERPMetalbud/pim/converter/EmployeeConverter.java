package com.lelakowski.ERPMetalbud.pim.converter;

import com.lelakowski.ERPMetalbud.pim.domain.model.Account;
import com.lelakowski.ERPMetalbud.pim.domain.model.Employee;
import com.lelakowski.ERPMetalbud.pim.web.command.CreateAccountCommand;
import com.lelakowski.ERPMetalbud.pim.web.command.CreateEmployeeCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeeConverter {

    public Employee from(CreateEmployeeCommand createEmployeeCommand) {
        if (createEmployeeCommand == null) throw new IllegalArgumentException();
        return Employee.builder()
                .email(createEmployeeCommand.getEmail())
                .profession(createEmployeeCommand.getProfession())
                .employmentDate(createEmployeeCommand.getEmploymentDate())
                .salaryGross(createEmployeeCommand.getSalaryGross())
                .build();
    }

}

package com.lelakowski.ERPMetalbud.pim.converter;

import com.lelakowski.ERPMetalbud.pe.repository.PriceRepository;
import com.lelakowski.ERPMetalbud.pim.domain.model.Employee;
import com.lelakowski.ERPMetalbud.pim.domain.repository.ProfessionRepository;
import com.lelakowski.ERPMetalbud.pim.web.command.CreateEmployeeCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeeConverter {

    private final PriceRepository priceRepository;
    private final ProfessionRepository professionRepository;

    public Employee from(CreateEmployeeCommand createEmployeeCommand) {
        if (createEmployeeCommand == null) throw new IllegalArgumentException();
        return Employee.builder()
                .email(createEmployeeCommand.getEmail())
                .profession(professionRepository.getOne(createEmployeeCommand.getProfessionId()))
                .employmentDate(createEmployeeCommand.getEmploymentDate())
                .salaryGross(priceRepository.getOne(createEmployeeCommand.getPriceId()))
                .build();
    }

}

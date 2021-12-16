package com.lelakowski.ERPMetalbud.pim.service;

import com.lelakowski.ERPMetalbud.pe.domain.model.Price;
import com.lelakowski.ERPMetalbud.pe.domain.repository.PriceRepository;
import com.lelakowski.ERPMetalbud.pe.service.PriceService;
import com.lelakowski.ERPMetalbud.pim.builder.EmployeeBuilder;
import com.lelakowski.ERPMetalbud.pim.domain.model.Employee;
import com.lelakowski.ERPMetalbud.pim.domain.model.Profession;
import com.lelakowski.ERPMetalbud.pim.domain.repository.EmployeeRepository;
import com.lelakowski.ERPMetalbud.pim.domain.repository.ProfessionRepository;
import com.lelakowski.ERPMetalbud.pim.validation.CreateEmployeeValidator;
import com.lelakowski.ERPMetalbud.pim.web.command.CreateEmployeeCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeBuilder employeeBuilder;
    private final PriceService priceService;
    private final ProfessionRepository professionRepository;
    private final PriceRepository priceRepository;
    private final CreateEmployeeValidator createEmployeeValidator;


    @Override
    public Long saveEmployee(CreateEmployeeCommand createEmployeeCommand) {
        createEmployeeValidator.validate(createEmployeeCommand);
        Profession profession = professionRepository.getOne(createEmployeeCommand.getProfessionId());

        Long priceId = priceService.savePrice(
                createEmployeeCommand.getPriceValue(),
                createEmployeeCommand.getPriceCurrency()
        );
        Price salaryGross = priceRepository.getOne(priceId);

        Employee employeeToSave = employeeBuilder.from(
                createEmployeeCommand.getEmail(),
                profession,
                createEmployeeCommand.getEmploymentDate(),
                salaryGross
        );

        Employee employee = employeeRepository.save(employeeToSave);
        return employee.getId();
    }

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

}

package com.lelakowski.ERPMetalbud.pim.service;

import com.lelakowski.ERPMetalbud.pe.domain.model.Price;
import com.lelakowski.ERPMetalbud.pe.domain.repository.PriceRepository;
import com.lelakowski.ERPMetalbud.pim.builder.EmployeeBuilder;
import com.lelakowski.ERPMetalbud.pim.domain.model.Employee;
import com.lelakowski.ERPMetalbud.pim.domain.model.Profession;
import com.lelakowski.ERPMetalbud.pim.domain.repository.EmployeeRepository;
import com.lelakowski.ERPMetalbud.pim.domain.repository.ProfessionRepository;
import com.lelakowski.ERPMetalbud.pim.web.command.CreateEmployeeCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeBuilder employeeBuilder;
    private final PriceRepository priceRepository;
    private final ProfessionRepository professionRepository;

    @Transactional
    @Override
    public Long saveEmployee(CreateEmployeeCommand createEmployeeCommand) {
        Profession profession = professionRepository.getOne(createEmployeeCommand.getProfessionId());
        Price salaryGross = priceRepository.getOne(createEmployeeCommand.getPriceId());

        Employee employeeToSave = employeeBuilder.from(
                createEmployeeCommand.getEmail(),
                profession,
                createEmployeeCommand.getEmploymentDate(),
                salaryGross
        );

        Employee employee = employeeRepository.save(employeeToSave);
        saveReferences(employee, profession, salaryGross);

        return employee.getId();
    }

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    private void saveReferences(Employee employeeReference, Profession profession, Price salaryGross) {
        profession.addToEmployeeList(employeeReference);
        salaryGross.addToEmployeeList(employeeReference);
    }


}

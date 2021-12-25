package com.lelakowski.ERPMetalbud.pim.service;

import com.lelakowski.ERPMetalbud.pim.builder.EmployeeBuilder;
import com.lelakowski.ERPMetalbud.pim.domain.model.Employee;
import com.lelakowski.ERPMetalbud.pim.domain.model.Profession;
import com.lelakowski.ERPMetalbud.pim.domain.repository.EmployeeRepository;
import com.lelakowski.ERPMetalbud.pim.domain.repository.ProfessionRepository;
import com.lelakowski.ERPMetalbud.pim.notification.NotFoundEmployeeWithExternalNameException;
import com.lelakowski.ERPMetalbud.pim.validation.CreateEmployeeValidator;
import com.lelakowski.ERPMetalbud.pim.web.command.CreateEmployeeCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeBuilder employeeBuilder;
    private final ProfessionRepository professionRepository;
    private final CreateEmployeeValidator createEmployeeValidator;
    private final PriceApiClient priceApiClient;

    private void sendCreatePriceCommand(CreateEmployeeCommand createEmployeeCommand) {
        priceApiClient.createPrice(createEmployeeCommand.getPrice());
    }

    private Long createPrice(CreateEmployeeCommand createEmployeeCommand) {
        sendCreatePriceCommand(createEmployeeCommand);
        return priceApiClient.getPriceIdByExternalName(createEmployeeCommand.getPrice().getExternalName());
    }

    @Transactional
    @Override
    public Long saveEmployee(CreateEmployeeCommand createEmployeeCommand) {
        createEmployeeValidator.validate(createEmployeeCommand);
        Profession profession = professionRepository.getOne(createEmployeeCommand.getProfessionId());

        Long priceId = createPrice(createEmployeeCommand);

        Employee employeeToSave = employeeBuilder.from(
                createEmployeeCommand.getExternalName(),
                createEmployeeCommand.getEmail(),
                profession,
                createEmployeeCommand.getEmploymentDate(),
                priceId
        );

        Employee employee = employeeRepository.save(employeeToSave);
        return employee.getId();
    }

    @Override
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Long getEmployeeIdByExternalName(String externalName) {
        Optional<Long> employeeIdOpt = employeeRepository.findEmployeeIdByCaption(externalName);
        if (employeeIdOpt.isEmpty()) throw new NotFoundEmployeeWithExternalNameException(externalName);
        return employeeIdOpt.get();
    }

}

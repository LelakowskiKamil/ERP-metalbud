package com.lelakowski.ERPMetalbud.pim.testkit;

import com.lelakowski.ERPMetalbud.pe.domain.model.Price;
import com.lelakowski.ERPMetalbud.pe.testkit.PriceMocks;
import com.lelakowski.ERPMetalbud.pim.domain.model.Employee;
import com.lelakowski.ERPMetalbud.pim.domain.model.Profession;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EmployeeMocks {


    public static Employee employee1 = mockEmployee(1L, "employee1@email.com", ProfessionMocks.profession1, PriceMocks.price1);
    public static Employee employee2 = mockEmployee(2L, "employee2@email.com", ProfessionMocks.profession2, PriceMocks.price2);
    public static Employee employee3 = mockEmployee(3L, "employee3@email.com", ProfessionMocks.profession3, PriceMocks.price3);
    public static Employee employee4 = mockEmployee(4L, "employee4@email.com", ProfessionMocks.profession4, PriceMocks.price4);

    private static Employee mockEmployee(Long id, String email, Profession profession, Price price) {
        Employee employee = mock(Employee.class);
        when(employee.getId()).thenReturn(id);
        when(employee.getEmploymentDate()).thenReturn("2020-10-10");
        when(employee.getSalaryGross()).thenReturn(price);
        when(employee.getProfession()).thenReturn(profession);
        when(employee.getEmail()).thenReturn(email);
        return employee;
    }
}

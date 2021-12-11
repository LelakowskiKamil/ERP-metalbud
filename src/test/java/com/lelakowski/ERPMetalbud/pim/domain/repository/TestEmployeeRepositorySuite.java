package com.lelakowski.ERPMetalbud.pim.domain.repository;

import com.lelakowski.ERPMetalbud.pim.domain.model.Employee;
import com.lelakowski.ERPMetalbud.pim.testkit.EmployeeMocks;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class TestEmployeeRepositorySuite {

    public final Employee employee1 = EmployeeMocks.employee1;
    public final Employee employee2 = EmployeeMocks.employee2;
    public final Employee employee3 = EmployeeMocks.employee3;
    public final Employee employee4 = EmployeeMocks.employee4;

    private final TreeMap<Long, Employee> employeeData = initData();
    private final EmployeeRepository employeeRepository = new TestEmployeeRepository(employeeData);

    @Test
    @DisplayName(".getOne() method test")
    void test1() {
        Assertions.assertEquals(employeeRepository.getOne(employee1.getId()), employee1);
        Assertions.assertEquals(employeeRepository.getOne(employee2.getId()), employee2);
        Assertions.assertEquals(employeeRepository.getOne(employee3.getId()), employee3);

        Assertions.assertEquals(employeeRepository.getOne(5L), null);
    }

    @Test
    @DisplayName(".findAll() method test")
    void test2() {
        List<Employee> expectedList = Arrays.asList(employee1, employee2, employee3, employee4);
        List<Employee> actualList = employeeRepository.findAll();
        Assertions.assertEquals(actualList.size(), expectedList.size());
        Assertions.assertTrue(expectedList.containsAll(actualList));
        Assertions.assertTrue(actualList.containsAll(expectedList));
    }

    @Test
    @DisplayName(".existsById() method test")
    void test3() {
        Assertions.assertTrue(employeeRepository.existsById(employee1.getId()));
        Assertions.assertTrue(employeeRepository.existsById(employee2.getId()));
        Assertions.assertTrue(employeeRepository.existsById(employee3.getId()));
        Assertions.assertTrue(employeeRepository.existsById(employee4.getId()));
        Assertions.assertFalse(employeeRepository.existsById(5L));
    }


    private TreeMap<Long, Employee> initData() {
        TreeMap<Long, Employee> data = new TreeMap<>();
        data.put(employee1.getId(), employee1);
        data.put(employee2.getId(), employee2);
        data.put(employee3.getId(), employee3);
        data.put(employee4.getId(), employee4);
        return data;
    }

}
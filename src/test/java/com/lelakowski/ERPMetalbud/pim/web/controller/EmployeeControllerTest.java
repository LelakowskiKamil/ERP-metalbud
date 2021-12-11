package com.lelakowski.ERPMetalbud.pim.web.controller;

import com.google.gson.Gson;
import com.lelakowski.ERPMetalbud.pe.domain.model.Price;
import com.lelakowski.ERPMetalbud.pe.domain.repository.PriceRepository;
import com.lelakowski.ERPMetalbud.pim.domain.model.Employee;
import com.lelakowski.ERPMetalbud.pim.domain.model.Profession;
import com.lelakowski.ERPMetalbud.pim.domain.repository.EmployeeRepository;
import com.lelakowski.ERPMetalbud.pim.domain.repository.ProfessionRepository;
import com.lelakowski.ERPMetalbud.pim.web.command.CreateEmployeeCommand;
import com.lelakowski.ERPMetalbud.pim.web.command.CreatePrivilegesCommand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class EmployeeControllerTest {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    PriceRepository priceRepository;

    @Autowired
    ProfessionRepository professionRepository;

    @Autowired
    MockMvc mockMvc;

    String endpoint = "/employees";

    @DisplayName("- should got employees view on GET request")
    @Test
    void test1() throws Exception {
        Profession profession = new Profession("test", Collections.emptyList());
        professionRepository.save(profession);
        Price price = new Price(1.5, "PLN", Collections.emptyList(), Collections.emptyList());
        priceRepository.save(price);
        Employee employee = new Employee("email@email.pl", profession, "10-10-2020", price);
        employeeRepository.save(employee);
        String expectedContent = "[{\"id\":1,\"email\":\"email@email.pl\",\"profession\":{\"id\":1,\"designation\":\"test\"},\"employmentDate\":\"10-10-2020\",\"salaryGross\":{\"id\":1,\"value\":1.5,\"currency\":\"PLN\"}}]";

        mockMvc.perform(MockMvcRequestBuilders.get(endpoint))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedContent));
    }

    @Test
    @DisplayName("- should create employee when data is correct")
    void test2() throws Exception {
        Profession profession = new Profession("test", Collections.emptyList());
        professionRepository.save(profession);
        CreateEmployeeCommand command = new CreateEmployeeCommand("email@email.pl", 1L, "10-10-2020", 1.5, "PLN");
        Gson gson = new Gson();
        String json = gson.toJson(command);
        System.out.println(json);

        mockMvc.perform(MockMvcRequestBuilders.post(endpoint)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .with(SecurityMockMvcRequestPostProcessors.anonymous())
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        String expectedContent = "[{\"id\":1,\"email\":\"email@email.pl\",\"profession\":{\"id\":1,\"designation\":\"test\"},\"employmentDate\":\"10-10-2020\",\"salaryGross\":{\"id\":1,\"value\":1.5,\"currency\":\"PLN\"}}]";
        mockMvc.perform(MockMvcRequestBuilders.get(endpoint))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedContent));
    }

    @Test
    @DisplayName("- should throw error when data is incorrect correct")
    void test3() throws Exception {
        CreatePrivilegesCommand command = new CreatePrivilegesCommand("A", true, true, true, true);

        Gson gson = new Gson();
        String json = gson.toJson(command);
        mockMvc.perform(MockMvcRequestBuilders.post(endpoint)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .with(SecurityMockMvcRequestPostProcessors.anonymous())
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

}
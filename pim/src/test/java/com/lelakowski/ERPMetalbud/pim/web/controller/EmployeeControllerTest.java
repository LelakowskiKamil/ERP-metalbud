package com.lelakowski.ERPMetalbud.pim.web.controller;

import com.google.gson.Gson;
import com.lelakowski.ERPMetalbud.ProductInformationManagementApplication;
import com.lelakowski.ERPMetalbud.pim.domain.model.Employee;
import com.lelakowski.ERPMetalbud.pim.domain.model.Profession;
import com.lelakowski.ERPMetalbud.pim.domain.repository.EmployeeRepository;
import com.lelakowski.ERPMetalbud.pim.domain.repository.ProfessionRepository;
import com.lelakowski.ERPMetalbud.pim.service.PriceApiClient;
import com.lelakowski.ERPMetalbud.pim.web.command.CreateEmployeeCommand;
import com.lelakowski.ERPMetalbud.pim.web.command.CreatePimPriceCommand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ProductInformationManagementApplication.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class EmployeeControllerTest {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ProfessionRepository professionRepository;

    @MockBean
    PriceApiClient priceApiClient;

    Gson gson = new Gson();

    @Autowired
    MockMvc mockMvc;

    String endpoint = "/employees";

    @DisplayName("- should got employees view on GET request")
    @Test
    void test1() throws Exception {
        Profession profession = new Profession("test");
        professionRepository.save(profession);

        String employmentDate = fromGregorianCalendar(new GregorianCalendar(2020, 1, 1, 0, 0, 0));
        Employee employee = new Employee("externalName1", "email@email.pl", profession, employmentDate, 1L);
        employeeRepository.save(employee);
        String expectedContent = "[{\"id\":1,\"externalName\":\"externalName1\",\"email\":\"email@email.pl\",\"profession\":{\"id\":1,\"designation\":\"test\"},\"employmentDate\":\"2020-02-01 00:00:00\",\"salaryId\":1}]";

        mockMvc.perform(MockMvcRequestBuilders.get(endpoint))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedContent));
    }

    @Test
    @DisplayName("- should create employee when data is correct")
    void test2() throws Exception {
        Profession profession = new Profession("test");
        professionRepository.save(profession);
        String employmentDate = fromGregorianCalendar(new GregorianCalendar(2020, 1, 1, 0, 0, 0));
        CreatePimPriceCommand price = new CreatePimPriceCommand("price1", 1.5, "PLN");
        CreateEmployeeCommand command = new CreateEmployeeCommand("externalName2", "email@email.pl", 1L, employmentDate, price);

        String json = gson.toJson(command);

        ResponseEntity<ResponseEntity> responseEntity = Mockito.mock(ResponseEntity.class);
        Mockito.when(priceApiClient.createPrice(any(CreatePimPriceCommand.class))).thenReturn(responseEntity);
        Mockito.when(priceApiClient.getPriceIdByExternalName(any())).thenReturn(1L);


        mockMvc.perform(MockMvcRequestBuilders.post(endpoint)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .with(SecurityMockMvcRequestPostProcessors.anonymous())
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        String expectedContent = "[{\"id\":1,\"externalName\":\"externalName2\",\"email\":\"email@email.pl\",\"profession\":{\"id\":1,\"designation\":\"test\"},\"employmentDate\":\"2020-02-01 00:00:00\",\"salaryId\":1}]";
        mockMvc.perform(MockMvcRequestBuilders.get(endpoint))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedContent));
    }

    @Test
    @DisplayName("- should throw error when data is incorrect correct - wrong email syntax")
    void test3() throws Exception {
        Profession profession = new Profession("test");
        professionRepository.save(profession);
        String employmentDate = fromGregorianCalendar(new GregorianCalendar(2020, 1, 1, 0, 0, 0));
        CreatePimPriceCommand price = new CreatePimPriceCommand("price1", 1.5, "PLN");
        CreateEmployeeCommand command = new CreateEmployeeCommand("externalName3", "email@email.pl", 10L, employmentDate, price);

        String json = gson.toJson(command);

        ResponseEntity<ResponseEntity> responseEntity = Mockito.mock(ResponseEntity.class);
        Mockito.when(priceApiClient.createPrice(any(CreatePimPriceCommand.class))).thenReturn(responseEntity);
        Mockito.when(priceApiClient.getPriceIdByExternalName(any())).thenReturn(1L);

        mockMvc.perform(MockMvcRequestBuilders.post(endpoint)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .with(SecurityMockMvcRequestPostProcessors.anonymous())
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    private SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private String fromGregorianCalendar(GregorianCalendar date) {
        return sdfDate.format(date.getTime());
    }

    private String currentDate() {
        Date now = new Date();
        return sdfDate.format(now);
    }

}
package com.lelakowski.ERPMetalbud.pim.web.controller;

import com.google.gson.Gson;
import com.lelakowski.ERPMetalbud.ProductInformationManagementApplication;
import com.lelakowski.ERPMetalbud.pim.domain.model.Account;
import com.lelakowski.ERPMetalbud.pim.domain.model.Address;
import com.lelakowski.ERPMetalbud.pim.domain.model.Customer;
import com.lelakowski.ERPMetalbud.pim.domain.model.Privileges;
import com.lelakowski.ERPMetalbud.pim.domain.repository.AccountRepository;
import com.lelakowski.ERPMetalbud.pim.domain.repository.AddressRepository;
import com.lelakowski.ERPMetalbud.pim.domain.repository.CustomerRepository;
import com.lelakowski.ERPMetalbud.pim.domain.repository.PrivilegesRepository;
import com.lelakowski.ERPMetalbud.pim.web.command.CreateCustomerCommand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ProductInformationManagementApplication.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class CustomerControllerTest {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    PrivilegesRepository privilegesRepository;
    @Autowired
    AddressRepository addressRepository;

    @Autowired
    MockMvc mockMvc;

    String endpoint = "/customers";

    @DisplayName("- should got customers view on GET request")
    @Test
    void test1() throws Exception {
        Privileges privileges = new Privileges("Admin", true, true, true, true);
        privilegesRepository.save(privileges);
        Account account = new Account("externalName1", "admin", "!Admin123", "admin@admin.pl", privileges);
        accountRepository.save(account);
        Address address = new Address("Mielec", "12343424", "Podkarpackie", "Poland");
        addressRepository.save(address);
        Customer customer = new Customer("externalName1", "Kamil", "Lelakowski", account, address);
        customerRepository.save(customer);

        String expectedContent = "[{\"id\":1,\"externalName\":\"externalName1\",\"name\":\"Kamil\",\"surname\":\"Lelakowski\",\"account\":{\"id\":1,\"externalName\":\"externalName1\",\"username\":\"admin\",\"password\":\"!Admin123\",\"email\":\"admin@admin.pl\",\"privileges\":{\"id\":1,\"caption\":\"Admin\",\"canView\":true,\"canCreate\":true,\"canUpdate\":true,\"canRemove\":true}},\"address\":{\"id\":1,\"city\":\"Mielec\",\"postalCode\":\"12343424\",\"state\":\"Podkarpackie\",\"country\":\"Poland\"}}]";

        mockMvc.perform(MockMvcRequestBuilders.get(endpoint))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedContent));
    }

    @Test
    @DisplayName("- should create customer when data is correct")
    void test2() throws Exception {
        Privileges privileges = new Privileges("Admin", true, true, true, true);
        privilegesRepository.save(privileges);
        Account account = new Account("externalName2", "admin", "!Admin123", "admin@admin.pl", privileges);
        accountRepository.save(account);
        Address address = new Address("Mielec", "12343424", "Podkarpackie", "Poland");
        addressRepository.save(address);

        CreateCustomerCommand command = new CreateCustomerCommand("externalName2", "Kamil", "Lelakowski", 1L, 1L);
        String expectedContent = "[{\"id\":1,\"externalName\":\"externalName2\",\"name\":\"Kamil\",\"surname\":\"Lelakowski\",\"account\":{\"id\":1,\"externalName\":\"externalName2\",\"username\":\"admin\",\"password\":\"!Admin123\",\"email\":\"admin@admin.pl\",\"privileges\":{\"id\":1,\"caption\":\"Admin\",\"canView\":true,\"canCreate\":true,\"canUpdate\":true,\"canRemove\":true}},\"address\":{\"id\":1,\"city\":\"Mielec\",\"postalCode\":\"12343424\",\"state\":\"Podkarpackie\",\"country\":\"Poland\"}}]";

        Gson gson = new Gson();
        String json = gson.toJson(command);
        mockMvc.perform(MockMvcRequestBuilders.post(endpoint)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .with(SecurityMockMvcRequestPostProcessors.anonymous())
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        mockMvc.perform(MockMvcRequestBuilders.get(endpoint))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedContent));
    }

    @Test
    @DisplayName("- should throw error when data is incorrect correct")
    void test3() throws Exception {
        CreateCustomerCommand command = new CreateCustomerCommand("externalName3", "K", "Lelakowski", 1L, 1L);

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
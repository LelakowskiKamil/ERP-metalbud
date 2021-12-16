package com.lelakowski.ERPMetalbud.pim.web.controller;

import com.google.gson.Gson;
import com.lelakowski.ERPMetalbud.ProductInformationManagementApplication;
import com.lelakowski.ERPMetalbud.pim.domain.model.Account;
import com.lelakowski.ERPMetalbud.pim.domain.model.Privileges;
import com.lelakowski.ERPMetalbud.pim.domain.repository.AccountRepository;
import com.lelakowski.ERPMetalbud.pim.domain.repository.PrivilegesRepository;
import com.lelakowski.ERPMetalbud.pim.web.command.CreateAccountCommand;
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
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = ProductInformationManagementApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class AccountControllerTest {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    PrivilegesRepository privilegesRepository;

    @Autowired
    MockMvc mockMvc;

    String endpoint = "/accounts";

    @DisplayName("- should got accounts view on GET request")
    @Test
    void test1() throws Exception {
        Privileges privileges = new Privileges("Admin", true, true, true, true);
        privilegesRepository.save(privileges);
        Account account = new Account("admin", "!Admin123", "admin@admin.pl", privileges);
        accountRepository.save(account);
        String expectedContent = "[{\"id\":1,\"username\":\"admin\",\"password\":\"!Admin123\",\"email\":\"admin@admin.pl\",\"privileges\":{\"id\":1,\"caption\":\"Admin\",\"canView\":true,\"canCreate\":true,\"canUpdate\":true,\"canRemove\":true}}]";
        mockMvc.perform(MockMvcRequestBuilders.get(endpoint))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedContent));
    }

    @Test
    @DisplayName("- should create account when data is correct")
    void test2() throws Exception {
        CreateAccountCommand command = new CreateAccountCommand("admin", "!Admin123", "admin@admin.pl", 1L);

        Privileges privileges = new Privileges("Admin", true, true, true, true);
        privilegesRepository.save(privileges);
        String expectedContent = "[{\"id\":1,\"username\":\"admin\",\"password\":\"!Admin123\",\"email\":\"admin@admin.pl\",\"privileges\":{\"id\":1,\"caption\":\"Admin\",\"canView\":true,\"canCreate\":true,\"canUpdate\":true,\"canRemove\":true}}]";

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
        CreateAccountCommand command = new CreateAccountCommand("admin", "admin", "adminadminpl", 1L);
        Privileges privileges = new Privileges("Admin", true, true, true, true);
        privilegesRepository.save(privileges);

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
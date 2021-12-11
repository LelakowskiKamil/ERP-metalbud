package com.lelakowski.ERPMetalbud.pi.web.controller;

import com.google.gson.Gson;
import com.lelakowski.ERPMetalbud.pi.domain.model.Vendor;
import com.lelakowski.ERPMetalbud.pi.domain.repository.VendorRepository;
import com.lelakowski.ERPMetalbud.pi.web.command.CreateVendorCommand;
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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class VendorControllerTest {

    @Autowired
    VendorRepository vendorRepository;

    @Autowired
    MockMvc mockMvc;

    String endpoint = "/vendors";

    @DisplayName("- should got vendors view on GET request")
    @Test
    void test1() throws Exception {
        Vendor vendor1 = new Vendor("test", Collections.emptyList());
        Vendor vendor2 = new Vendor("test2", Collections.emptyList());
        Vendor vendor3 = new Vendor("test3", Collections.emptyList());
        List<Vendor> vendors = Arrays.asList(vendor1, vendor2, vendor3);
        vendorRepository.saveAll(vendors);

        String expectedContent = "[{\"id\":1,\"caption\":\"test\"},{\"id\":2,\"caption\":\"test2\"},{\"id\":3,\"caption\":\"test3\"}]";
        mockMvc.perform(MockMvcRequestBuilders.get(endpoint))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedContent));
    }


    @Test
    @DisplayName("- should create vendor when data is correct")
    void test2() throws Exception {
        CreateVendorCommand command = new CreateVendorCommand("test");
        Gson gson = new Gson();
        String json = gson.toJson(command);
        mockMvc.perform(MockMvcRequestBuilders.post(endpoint)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .with(SecurityMockMvcRequestPostProcessors.anonymous())
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        String expectedContent = "[{\"id\":1,\"caption\":\"test\"}]";
        mockMvc.perform(MockMvcRequestBuilders.get(endpoint))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedContent));


    }

    @Test
    @DisplayName("- should throw error when data is incorrect correct")
    void test3() throws Exception {
        CreateVendorCommand command = new CreateVendorCommand();

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
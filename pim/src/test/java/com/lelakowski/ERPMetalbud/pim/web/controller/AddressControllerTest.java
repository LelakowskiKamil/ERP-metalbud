package com.lelakowski.ERPMetalbud.pim.web.controller;

import com.google.gson.Gson;
import com.lelakowski.ERPMetalbud.ProductInformationManagementApplication;
import com.lelakowski.ERPMetalbud.pim.domain.model.Address;
import com.lelakowski.ERPMetalbud.pim.domain.repository.AddressRepository;
import com.lelakowski.ERPMetalbud.pim.web.command.CreateAddressCommand;
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

import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ProductInformationManagementApplication.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class AddressControllerTest {

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    MockMvc mockMvc;

    String endpoint = "/addresses";

    @DisplayName("- should got addresses view on GET request")
    @Test
    void test1() throws Exception {
        Address address = new Address("Mielec", "12343424", "Podkarpackie", "Poland");
        Address address2 = new Address("Mielec2", "123434245", "Mazowieckie", "Poland");
        Address address3 = new Address("Mielec3", "123434246", "Pomorskie", "Poland");
        addressRepository.saveAll(List.of(address, address2, address3));
        String expectedContent = "[{\"id\":1,\"city\":\"Mielec\",\"postalCode\":\"12343424\",\"state\":\"Podkarpackie\",\"country\":\"Poland\"},{\"id\":2,\"city\":\"Mielec2\",\"postalCode\":\"123434245\",\"state\":\"Mazowieckie\",\"country\":\"Poland\"},{\"id\":3,\"city\":\"Mielec3\",\"postalCode\":\"123434246\",\"state\":\"Pomorskie\",\"country\":\"Poland\"}]";
        mockMvc.perform(MockMvcRequestBuilders.get(endpoint))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedContent));
    }

    @Test
    @DisplayName("- should create address when data is correct")
    void test2() throws Exception {
        CreateAddressCommand command = new CreateAddressCommand("Mielec", "12343424", "Podkarpackie", "Poland");

        String expectedContent = "[{\"id\":1,\"city\":\"Mielec\",\"postalCode\":\"12343424\",\"state\":\"Podkarpackie\",\"country\":\"Poland\"}]";

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
        CreateAddressCommand command = new CreateAddressCommand("M", "12343424", "Podkarpackie", "Poland");

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
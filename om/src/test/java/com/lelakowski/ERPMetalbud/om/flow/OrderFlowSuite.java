package com.lelakowski.ERPMetalbud.om.flow;

import com.google.gson.Gson;
import com.lelakowski.ERPMetalbud.OrderManagementApplication;
import com.lelakowski.ERPMetalbud.om.domain.model.OrderStatus;
import com.lelakowski.ERPMetalbud.om.domain.model.ProductOrder;
import com.lelakowski.ERPMetalbud.om.domain.repository.repository.OrderRepository;
import com.lelakowski.ERPMetalbud.om.web.command.CreateOrderCommand;
import com.lelakowski.ERPMetalbud.om.web.command.CreateOrderItemCommand;
import org.junit.jupiter.api.Assertions;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = OrderManagementApplication.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class OrderFlowSuite {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    MockMvc mockMvc;

    final String ORDERS = "/orders";
    final String ADD_ORDER_ITEM = "/addorderitem";
    final String CONFIRM = "/confirm";
    final String SEND = "/send";
    final String CLOSE = "/close";
    final String REVERT = "/revert";


    @Test
    @DisplayName("- create order with some order items and next add another order item to order")
    void test1() throws Exception {
        List<CreateOrderItemCommand> orderItems = List.of(new CreateOrderItemCommand(1, 1L));

        CreateOrderCommand createOrderCommand = new CreateOrderCommand(1L, orderItems);
        Gson gson = new Gson();
        String json = gson.toJson(createOrderCommand);
        mockMvc.perform(MockMvcRequestBuilders.post(ORDERS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .with(SecurityMockMvcRequestPostProcessors.anonymous())
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        ProductOrder createdOrder = orderRepository.getOne(1L);

        CreateOrderItemCommand createOrderItemCommand = new CreateOrderItemCommand(2, 2L, createdOrder.getId());
        String json2 = gson.toJson(createOrderItemCommand);
        String addOrderItemEndpoint = ORDERS + "/1" + ADD_ORDER_ITEM;
        mockMvc.perform(MockMvcRequestBuilders.post(addOrderItemEndpoint)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json2)
                        .with(SecurityMockMvcRequestPostProcessors.anonymous())
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        ProductOrder createdOrderAfterAddingOrderItem = orderRepository.getOne(1L);
        Assertions.assertEquals(createdOrderAfterAddingOrderItem.getProductOrderItems().size(), 2);
    }

    @Test
    @DisplayName("- create order with some order items and confirm order")
    void test2() throws Exception {

        List<CreateOrderItemCommand> orderItems = List.of(new CreateOrderItemCommand(1, 1L));

        CreateOrderCommand createOrderCommand = new CreateOrderCommand(1L, orderItems);
        Gson gson = new Gson();
        String json = gson.toJson(createOrderCommand);
        mockMvc.perform(MockMvcRequestBuilders.post(ORDERS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .with(SecurityMockMvcRequestPostProcessors.anonymous())
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        String confirmEndpoint = ORDERS + "/1" + CONFIRM;

        mockMvc.perform(MockMvcRequestBuilders.post(confirmEndpoint)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .with(SecurityMockMvcRequestPostProcessors.anonymous())
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk());

        ProductOrder createdOrder = orderRepository.getOne(1L);
        Assertions.assertEquals(createdOrder.getStatus(), OrderStatus.CONFIRMED);
        Assertions.assertNotNull(createdOrder.getConfirmDate());
    }

    @Test
    @DisplayName("- correct order flow: create order with some order items, confirm, send and close order")
    void test3() throws Exception {

        List<CreateOrderItemCommand> orderItems = List.of(new CreateOrderItemCommand(1, 1L));

        CreateOrderCommand createOrderCommand = new CreateOrderCommand(1L, orderItems);
        Gson gson = new Gson();
        String json = gson.toJson(createOrderCommand);
        mockMvc.perform(MockMvcRequestBuilders.post(ORDERS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .with(SecurityMockMvcRequestPostProcessors.anonymous())
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        String confirmEndpoint = ORDERS + "/1" + CONFIRM;

        mockMvc.perform(MockMvcRequestBuilders.post(confirmEndpoint)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .with(SecurityMockMvcRequestPostProcessors.anonymous())
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk());

        ProductOrder createdOrder = orderRepository.getOne(1L);
        Assertions.assertEquals(createdOrder.getStatus(), OrderStatus.CONFIRMED);
        Assertions.assertNotNull(createdOrder.getConfirmDate());

        String sendEndpoint = ORDERS + "/1" + SEND;
        mockMvc.perform(MockMvcRequestBuilders.post(sendEndpoint)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .with(SecurityMockMvcRequestPostProcessors.anonymous())
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk());

        ProductOrder shippedOrder = orderRepository.getOne(1L);
        Assertions.assertEquals(shippedOrder.getStatus(), OrderStatus.SHIPPED);
        Assertions.assertNotNull(shippedOrder.getConfirmDate());

        String closeEndpoint = ORDERS + "/1" + CLOSE;
        mockMvc.perform(MockMvcRequestBuilders.post(closeEndpoint)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .with(SecurityMockMvcRequestPostProcessors.anonymous())
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk());

        ProductOrder closedOrder = orderRepository.getOne(1L);
        Assertions.assertEquals(closedOrder.getStatus(), OrderStatus.CLOSED);
        Assertions.assertNotNull(closedOrder.getCloseDate());
    }

    @Test
    @DisplayName("- incorrect order flow: cannot send unconfirmed order")
    void test4() throws Exception {

        List<CreateOrderItemCommand> orderItems = List.of(new CreateOrderItemCommand(1, 1L));

        CreateOrderCommand createOrderCommand = new CreateOrderCommand(1L, orderItems);
        Gson gson = new Gson();
        String json = gson.toJson(createOrderCommand);
        mockMvc.perform(MockMvcRequestBuilders.post(ORDERS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .with(SecurityMockMvcRequestPostProcessors.anonymous())
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        String sendEndpoint = ORDERS + "/1" + SEND;
        mockMvc.perform(MockMvcRequestBuilders.post(sendEndpoint)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .with(SecurityMockMvcRequestPostProcessors.anonymous())
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("- incorrect order flow: cannot revert not shipped order")
    void test5() throws Exception {

        List<CreateOrderItemCommand> orderItems = List.of(new CreateOrderItemCommand(1, 1L));

        CreateOrderCommand createOrderCommand = new CreateOrderCommand(1L, orderItems);
        Gson gson = new Gson();
        String json = gson.toJson(createOrderCommand);
        mockMvc.perform(MockMvcRequestBuilders.post(ORDERS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .with(SecurityMockMvcRequestPostProcessors.anonymous())
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        String confirmEndpoint = ORDERS + "/1" + CONFIRM;

        mockMvc.perform(MockMvcRequestBuilders.post(confirmEndpoint)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .with(SecurityMockMvcRequestPostProcessors.anonymous())
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk());

        ProductOrder createdOrder = orderRepository.getOne(1L);
        Assertions.assertEquals(createdOrder.getStatus(), OrderStatus.CONFIRMED);
        Assertions.assertNotNull(createdOrder.getConfirmDate());

        String sendEndpoint = ORDERS + "/1" + REVERT;
        mockMvc.perform(MockMvcRequestBuilders.post(sendEndpoint)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .with(SecurityMockMvcRequestPostProcessors.anonymous())
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

}
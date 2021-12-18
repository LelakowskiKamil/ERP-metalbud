package com.lelakowski.ERPMetalbud.om.flow;

import com.google.gson.Gson;
import com.lelakowski.ERPMetalbud.ProductInventoryApplication;
import com.lelakowski.ERPMetalbud.common.color.domain.model.Color;
import com.lelakowski.ERPMetalbud.common.color.domain.repository.ColorRepository;
import com.lelakowski.ERPMetalbud.common.dimension.domain.model.Dimension;
import com.lelakowski.ERPMetalbud.common.dimension.domain.model.Dimensions;
import com.lelakowski.ERPMetalbud.common.dimension.domain.repository.DimensionRepository;
import com.lelakowski.ERPMetalbud.common.dimension.domain.repository.DimensionsRepository;
import com.lelakowski.ERPMetalbud.om.domain.model.OrderStatus;
import com.lelakowski.ERPMetalbud.om.domain.model.ProductOrder;
import com.lelakowski.ERPMetalbud.om.domain.repository.repository.OrderItemRepository;
import com.lelakowski.ERPMetalbud.om.domain.repository.repository.OrderRepository;
import com.lelakowski.ERPMetalbud.om.web.command.CreateOrderCommand;
import com.lelakowski.ERPMetalbud.om.web.command.CreateOrderItemCommand;
import com.lelakowski.ERPMetalbud.pe.domain.model.Price;
import com.lelakowski.ERPMetalbud.pe.domain.repository.PriceRepository;
import com.lelakowski.ERPMetalbud.pi.domain.model.*;
import com.lelakowski.ERPMetalbud.pi.domain.repository.*;
import com.lelakowski.ERPMetalbud.pim.domain.model.Account;
import com.lelakowski.ERPMetalbud.pim.domain.model.Address;
import com.lelakowski.ERPMetalbud.pim.domain.model.Customer;
import com.lelakowski.ERPMetalbud.pim.domain.model.Privileges;
import com.lelakowski.ERPMetalbud.pim.domain.repository.AccountRepository;
import com.lelakowski.ERPMetalbud.pim.domain.repository.AddressRepository;
import com.lelakowski.ERPMetalbud.pim.domain.repository.CustomerRepository;
import com.lelakowski.ERPMetalbud.pim.domain.repository.PrivilegesRepository;
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
@ContextConfiguration(classes = ProductInventoryApplication.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class OrderFlowSuite {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    PrivilegesRepository privilegesRepository;
    @Autowired
    AddressRepository addressRepository;


    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductDetailsRepository productDetailsRepository;
    @Autowired
    PriceRepository priceRepository;
    @Autowired
    BrandRepository brandRepository;
    @Autowired
    ColorRepository colorRepository;
    @Autowired
    ProductSpecificationRepository productSpecificationRepository;
    @Autowired
    DimensionsRepository dimensionsRepository;
    @Autowired
    DimensionRepository dimensionRepository;
    @Autowired
    VendorRepository vendorRepository;

    @Autowired
    MockMvc mockMvc;

    final String ORDERS = "/orders";
    final String ADD_ORDER_ITEM = "/addorderitem";
    final String CONFIRM = "/confirm";
    final String SEND = "/send";
    final String CLOSE = "/close";
    final String REVERT = "/revert";


    private void prepareCustomerData() {
        Privileges privileges = new Privileges("Admin", true, true, true, true);
        privilegesRepository.save(privileges);
        Account account = new Account("admin", "!Admin123", "admin@admin.pl", privileges);
        accountRepository.save(account);
        Address address = new Address("Mielec", "12343424", "Podkarpackie", "Poland");
        addressRepository.save(address);
        Customer customer = new Customer("Kamil", "Lelakowski", account, address);
        customerRepository.save(customer);
    }

    private void prepareProductData() {
        Color color = new Color("Red", "Czerwony");
        colorRepository.save(color);
        Dimension dimensionHeight = new Dimension(1.5, "mm");
        Dimension dimensionWidth = new Dimension(1.5, "mm");
        Dimension dimensionLength = new Dimension(1.5, "mm");
        dimensionRepository.save(dimensionHeight);
        dimensionRepository.save(dimensionWidth);
        dimensionRepository.save(dimensionLength);
        Dimensions dimensions = new Dimensions("testDimension", dimensionHeight, dimensionWidth, dimensionLength);
        dimensionsRepository.save(dimensions);
        ProductSpecification productSpecification = new ProductSpecification("testProductSpec", dimensions);
        productSpecificationRepository.save(productSpecification);
        ProductDetails productDetails = new ProductDetails(color, productSpecification);
        productDetailsRepository.save(productDetails);
        Vendor vendor = new Vendor("vendor");
        vendorRepository.save(vendor);
        Brand brand = new Brand("brand");
        brandRepository.save(brand);
        Price price = new Price(1.5, "PLN");
        priceRepository.save(price);
        Product product1 = new Product("product", productDetails, vendor, brand, price);
        productRepository.save(product1);
        Product product2 = new Product("product2", productDetails, vendor, brand, price);
        productRepository.save(product2);
    }

    @Test
    @DisplayName("- create order with some order items and next add another order item to order")
    void test1() throws Exception {
        prepareCustomerData();
        prepareProductData();

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
        prepareCustomerData();
        prepareProductData();

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
        prepareCustomerData();
        prepareProductData();

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
        prepareCustomerData();
        prepareProductData();

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
        prepareCustomerData();
        prepareProductData();

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
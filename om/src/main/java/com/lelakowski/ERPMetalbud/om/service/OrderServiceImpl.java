package com.lelakowski.ERPMetalbud.om.service;

import com.lelakowski.ERPMetalbud.om.builder.ProductOrderBuilder;
import com.lelakowski.ERPMetalbud.om.domain.model.ProductOrder;
import com.lelakowski.ERPMetalbud.om.domain.model.ProductOrderItem;
import com.lelakowski.ERPMetalbud.om.domain.repository.repository.OrderItemRepository;
import com.lelakowski.ERPMetalbud.om.domain.repository.repository.OrderRepository;
import com.lelakowski.ERPMetalbud.om.validation.CreateOrderValidator;
import com.lelakowski.ERPMetalbud.om.web.command.CreateOrderCommand;
import com.lelakowski.ERPMetalbud.pim.domain.model.Account;
import com.lelakowski.ERPMetalbud.pim.domain.model.Customer;
import com.lelakowski.ERPMetalbud.pim.domain.model.Privileges;
import com.lelakowski.ERPMetalbud.pim.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductOrderBuilder productOrderBuilder;
    private final CreateOrderValidator createOrderValidator;
    private final CustomerRepository customerRepository;
    private final OrderItemService orderItemService;
    private final OrderItemRepository orderItemRepository;

    @Transactional
    @Override
    public Long saveOrder(CreateOrderCommand createOrderCommand) {
        createOrderValidator.validate(createOrderCommand);

        List<Long> productOrderItemIds = createOrderCommand.getOrderItems().stream()
                .map(orderItemService::saveOrderItem)
                .collect(Collectors.toList());
        List<ProductOrderItem> productOrderItems = orderItemRepository.findAllById(productOrderItemIds);

        Customer customer = customerRepository.getOne(createOrderCommand.getCustomerId());
        Privileges customerPrivileges = getPrivilegesForCustomer(customer);

        if (!customerPrivileges.getCanCreate()) throw new IllegalArgumentException();

        ProductOrder productOrderToSave = productOrderBuilder.from(
                createOrderCommand.getOrderDate(),
                customer
        );
        ProductOrder productOrder = orderRepository.save(productOrderToSave);
        saveReferences(productOrder, productOrderItems);

        return productOrder.getId();
    }

    @Override
    public List<ProductOrder> getOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<ProductOrder> getAllCustomerOrders(Long customerId) {
        return orderRepository.getOrdersForCustomer(customerId);
    }

    @Override
    public List<ProductOrderItem> getOrderItems(Long orderId) {
        return orderItemRepository.findAllByOrderId(orderId);
    }


    private Privileges getPrivilegesForCustomer(Customer customer) {
        Account customerAccount = customer.getAccount();
        return customerAccount.getPrivileges();
    }

    private void saveReferences(ProductOrder productOrder, List<ProductOrderItem> productOrderItems) {
        productOrderItems.forEach(orderItem -> orderItem.setProductOrder(productOrder));
    }
}

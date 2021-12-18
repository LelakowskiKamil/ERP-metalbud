package com.lelakowski.ERPMetalbud.om.service;

import com.lelakowski.ERPMetalbud.om.builder.ProductOrderBuilder;
import com.lelakowski.ERPMetalbud.om.domain.model.OrderStatus;
import com.lelakowski.ERPMetalbud.om.domain.model.ProductOrder;
import com.lelakowski.ERPMetalbud.om.domain.model.ProductOrderItem;
import com.lelakowski.ERPMetalbud.om.domain.repository.repository.OrderItemRepository;
import com.lelakowski.ERPMetalbud.om.domain.repository.repository.OrderRepository;
import com.lelakowski.ERPMetalbud.om.notification.IncorrectOrderStatusException;
import com.lelakowski.ERPMetalbud.om.validation.CreateOrderValidator;
import com.lelakowski.ERPMetalbud.om.web.command.CreateOrderCommand;
import com.lelakowski.ERPMetalbud.om.web.command.CreateOrderItemCommand;
import com.lelakowski.ERPMetalbud.pim.domain.model.Account;
import com.lelakowski.ERPMetalbud.pim.domain.model.Customer;
import com.lelakowski.ERPMetalbud.pim.domain.model.Privileges;
import com.lelakowski.ERPMetalbud.pim.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.lelakowski.ERPMetalbud.common.utils.DateTimeHelper.currentDate;

@Service
@Slf4j
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

        ProductOrder order = productOrderBuilder.emptyOrder();
        createOrderCommand.getOrderItems().forEach(orderItem -> orderItem.setOrderId(order.getId()));

        List<Long> productOrderItemIds = createOrderCommand.getOrderItems().stream()
                .map(orderItemService::saveOrderItem)
                .collect(Collectors.toList());
        List<ProductOrderItem> productOrderItems = orderItemRepository.findAllById(productOrderItemIds);

        Customer customer = customerRepository.getOne(createOrderCommand.getCustomerId());
        Privileges customerPrivileges = getPrivilegesForCustomer(customer);

        if (!customerPrivileges.getCanCreate()) throw new IllegalArgumentException();

        order.setCustomer(customer);
        ProductOrder productOrder = orderRepository.save(order);
        saveReferences(productOrder, productOrderItems);

        return productOrder.getId();
    }

    @Transactional
    @Override
    public Long addOrderItemToOrder(CreateOrderItemCommand createOrderItemCommand) {
        Long newOrderItemId = orderItemService.saveOrderItem(createOrderItemCommand);
        ProductOrderItem newProductOrderItem = orderItemRepository.getOne(newOrderItemId);

        ProductOrder productOrder = orderRepository.getOne(createOrderItemCommand.getOrderId());
        productOrder.addToProductOrderItemList(newProductOrderItem);
        return productOrder.getId();
    }

    @Transactional
    @Override
    public void confirm(Long orderId) {
        ProductOrder orderToConfirm = orderRepository.getOne(orderId);
        orderToConfirm.setStatus(OrderStatus.CONFIRMED);
        orderToConfirm.setConfirmDate(currentDate());
        log.debug("Order: " + orderToConfirm.getId() + " has been confirmed");
    }

    @Transactional
    @Override
    public void sendOffer(Long orderId) {
        ProductOrder orderToConfirm = orderRepository.getOne(orderId);
        if (orderToConfirm.getStatus() != OrderStatus.CONFIRMED && orderToConfirm.getConfirmDate() == null) {
            throw new IncorrectOrderStatusException(orderToConfirm.getStatus(), OrderStatus.CONFIRMED);
        }
        orderToConfirm.setStatus(OrderStatus.SHIPPED);
        orderToConfirm.setShipmentDate(currentDate());
        log.debug("Order: " + orderToConfirm.getId() + " has been shipped");
    }

    @Transactional
    @Override
    public void closeOffer(Long orderId) {
        ProductOrder orderToConfirm = orderRepository.getOne(orderId);
        orderToConfirm.setStatus(OrderStatus.CLOSED);
        orderToConfirm.setCloseDate(currentDate());
        log.debug("Order: " + orderToConfirm.getId() + " has been closed");
    }

    @Transactional
    @Override
    public void revertOffer(Long orderId) {
        ProductOrder orderToConfirm = orderRepository.getOne(orderId);
        if (orderToConfirm.getStatus() != OrderStatus.SHIPPED && orderToConfirm.getShipmentDate() == null) {
            throw new IncorrectOrderStatusException(orderToConfirm.getStatus(), OrderStatus.SHIPPED);
        }
        orderToConfirm.setStatus(OrderStatus.REVERTED);
        orderToConfirm.setRevertDate(currentDate());
        log.debug("Order: " + orderToConfirm.getId() + " has been reverted");
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

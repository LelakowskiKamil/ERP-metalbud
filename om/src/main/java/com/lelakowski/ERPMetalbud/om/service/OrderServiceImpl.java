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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductOrderBuilder productOrderBuilder;
    private final CreateOrderValidator createOrderValidator;
    private final OrderItemService orderItemService;
    private final OrderItemRepository orderItemRepository;

    @Transactional
    @Override
    public Long saveOrder(CreateOrderCommand createOrderCommand) {
        createOrderValidator.validate(createOrderCommand);

        ProductOrder order = productOrderBuilder.emptyOrder(createOrderCommand.getCustomerId());
        createOrderCommand.getOrderItems().forEach(orderItem -> orderItem.setOrderId(order.getId()));

        List<Long> productOrderItemIds = createOrderCommand.getOrderItems().stream()
                .map(orderItemService::saveOrderItem)
                .collect(Collectors.toList());
        List<ProductOrderItem> productOrderItems = orderItemRepository.findAllById(productOrderItemIds);

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

    private void saveReferences(ProductOrder productOrder, List<ProductOrderItem> productOrderItems) {
        productOrderItems.forEach(orderItem -> orderItem.setProductOrder(productOrder));
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

package com.lelakowski.ERPMetalbud.om.web.controller;

import com.lelakowski.ERPMetalbud.om.domain.model.ProductOrder;
import com.lelakowski.ERPMetalbud.om.domain.model.ProductOrderItem;
import com.lelakowski.ERPMetalbud.om.service.OrderService;
import com.lelakowski.ERPMetalbud.om.web.command.CreateOrderCommand;
import com.lelakowski.ERPMetalbud.om.web.command.CreateOrderItemCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/orders")
    public ResponseEntity<List> getOrders() {
        return new ResponseEntity(orderService.getOrders(), HttpStatus.OK);
    }

    @PostMapping(path = "/orders", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ProductOrder> createOrder(@RequestBody CreateOrderCommand createOrderCommand) {
        orderService.saveOrder(createOrderCommand);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(path = "/orders/{orderId}/addorderitem", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ProductOrderItem> addOrderItemToOrder(@PathVariable Long orderId, @RequestBody CreateOrderItemCommand createOrderItemCommand) {
        orderService.addOrderItemToOrder(createOrderItemCommand);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(path = "/orders/{orderId}/confirm", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ProductOrderItem> confirmOrder(@PathVariable Long orderId) {
        orderService.confirm(orderId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(path = "/orders/{orderId}/send", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ProductOrderItem> sendOrder(@PathVariable Long orderId) {
        orderService.sendOffer(orderId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(path = "/orders/{orderId}/close", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ProductOrderItem> closeOrder(@PathVariable Long orderId) {
        orderService.closeOffer(orderId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(path = "/orders/{orderId}/revert", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ProductOrderItem> revertOrder(@PathVariable Long orderId) {
        orderService.revertOffer(orderId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}

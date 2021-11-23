package com.lelakowski.ERPMetalbud.om.web.controller;

import com.lelakowski.ERPMetalbud.om.domain.model.ProductOrderItem;
import com.lelakowski.ERPMetalbud.om.service.OrderItemService;
import com.lelakowski.ERPMetalbud.om.service.OrderService;
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
public class OrderItemController {

    private final OrderItemService orderItemService;
    private final OrderService orderService;

    @GetMapping("/orderitems/{orderId}")
    public ResponseEntity<List> getOrderItemsForOrder(@PathVariable Long orderId) {
        return new ResponseEntity<>(orderService.getOrderItems(orderId), HttpStatus.OK);
    }


    @PostMapping(path = "/orderitems", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ProductOrderItem> createOrderItem(@RequestBody CreateOrderItemCommand createOrderItemCommand) {
        orderItemService.saveOrderItem(createOrderItemCommand);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}

package com.lelakowski.ERPMetalbud.om.web.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderItemCommand {

    @NotNull
    private Integer quantity;
    @NotNull
    private Long productId;
    private Long orderId;

    public CreateOrderItemCommand(Integer quantity, Long productId) {
        this.quantity = quantity;
        this.productId = productId;
    }
}

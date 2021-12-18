package com.lelakowski.ERPMetalbud.om.web.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderCommand {

    @NotNull
    private Long customerId;
    private List<CreateOrderItemCommand> orderItems;

}

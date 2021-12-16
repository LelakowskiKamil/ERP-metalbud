package com.lelakowski.ERPMetalbud.om.web.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderCommand {

    @NotBlank
    private Date orderDate;
    @NotNull
    private Long customerId;
    @NotEmpty
    private List<CreateOrderItemCommand> orderItems;

}

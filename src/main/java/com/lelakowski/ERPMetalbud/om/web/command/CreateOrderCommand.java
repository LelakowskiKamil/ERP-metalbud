package com.lelakowski.ERPMetalbud.om.web.command;

import com.lelakowski.ERPMetalbud.pi.domain.model.Product;
import com.lelakowski.ERPMetalbud.pim.domain.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
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

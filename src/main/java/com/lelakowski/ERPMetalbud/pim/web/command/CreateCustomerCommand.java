package com.lelakowski.ERPMetalbud.pim.web.command;

import com.lelakowski.ERPMetalbud.pim.domain.model.Account;
import com.lelakowski.ERPMetalbud.pim.domain.model.Address;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCustomerCommand {

    @NotBlank
    @Size(min = 5, max = 50)
    String name;
    @NotBlank
    @Size(min = 5, max = 50)
    String surname;
    @NotNull
    Long accountId;
    @NotNull
    Long addressId;

}

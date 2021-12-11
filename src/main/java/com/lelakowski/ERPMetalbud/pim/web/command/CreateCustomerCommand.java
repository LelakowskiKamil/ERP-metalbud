package com.lelakowski.ERPMetalbud.pim.web.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCustomerCommand {

    @NotBlank
    @Size(min = 2, max = 50)
    String name;
    @NotBlank
    @Size(min = 2, max = 50)
    String surname;
    @NotNull
    Long accountId;
    @NotNull
    Long addressId;

}

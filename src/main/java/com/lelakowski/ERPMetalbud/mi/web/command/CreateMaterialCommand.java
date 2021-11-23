package com.lelakowski.ERPMetalbud.mi.web.command;

import com.lelakowski.ERPMetalbud.om.web.command.CreateOrderItemCommand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateMaterialCommand {

    @NotBlank
    private String caption;

}

package com.lelakowski.ERPMetalbud.mi.web.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateMaterialCommand {

    @NotEmpty
    private String caption;

}

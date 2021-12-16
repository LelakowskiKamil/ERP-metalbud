package com.lelakowski.ERPMetalbud.pim.web.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateProfessionCommand {

    @NotBlank
    @Size(min = 2, max = 50)
    private String designation;

}

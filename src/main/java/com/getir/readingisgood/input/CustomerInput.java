package com.getir.readingisgood.input;

import com.getir.readingisgood.constants.Constants;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@ApiModel(value = "Input for customer register")
public class CustomerInput {

    @ApiModelProperty(value = "name for customer register")
    @NotEmpty(message = "Name Can Not Be Null")
    private String name;

    @ApiModelProperty(value = "surname for customer register")
    @NotEmpty(message = "Surname Can Not Be Null")
    private String surname;

    @ApiModelProperty(value = "email for customer register")
    @NotEmpty(message = "Email Can Not Be Null")
    private String email;

    @ApiModelProperty(value = "password for customer register")
    @NotEmpty(message = "Password Can Not Be Null")
    @Pattern(regexp = Constants.RegEx.ALPHANUMERIC_VALIDATION)
    private String password;

}

package com.getir.readingisgood.input;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import com.getir.readingisgood.constants.*;


@Getter
@Setter
@NoArgsConstructor
@ApiModel(value = "Authentication Input")
public class AuthenticationInput {

    @ApiModelProperty(value = "Email for Authentication")
    @NotEmpty(message = "Username may not be empty")
    @Pattern(regexp = Constants.RegEx.ALPHANUMERIC_VALIDATION)
    private String email;

    @ApiModelProperty(value = "Email for Authentication")
    @NotEmpty(message="Password may not be empty")
    @Pattern(regexp = Constants.RegEx.ALPHANUMERIC_VALIDATION)
    private String password;

}

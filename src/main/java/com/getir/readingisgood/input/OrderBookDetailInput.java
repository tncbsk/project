package com.getir.readingisgood.input;

import com.getir.readingisgood.constants.Constants;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@ApiModel(value = "Input for create new order's book detail")
public class OrderBookDetailInput {

    @Min(value = 0, message = "quantity should have at least 1")
    @ApiModelProperty(value = "quantity create new order's book detail")
    private Integer quantity;

    @NotEmpty(message = "Email Can Not Be Null")
    @ApiModelProperty(value = "bookId for create new order's book detail")
    @Pattern(regexp = Constants.RegEx.ALPHANUMERIC_VALIDATION)
    private String bookId;
}

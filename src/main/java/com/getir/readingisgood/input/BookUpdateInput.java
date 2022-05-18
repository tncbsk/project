package com.getir.readingisgood.input;

import com.getir.readingisgood.constants.Constants;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@ApiModel(value = "Input for update book")
public class BookUpdateInput {

    @NotNull(message = "Isbn number can not be null.")
    @Pattern(regexp = Constants.RegEx.NUMERIC_VALIDATION)
    @ApiModelProperty(value = "isbn for update book")
    private String isbn;

    @ApiModelProperty(value = "price for update book")
    @Min(value = 0, message = "Price should have at least 0")
    private Double price;

    @ApiModelProperty(value = "stock for update book")
    @Min(value = 0, message = "Stock should have at least 0")
    private Long stock;

}

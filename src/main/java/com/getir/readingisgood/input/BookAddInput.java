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
@ApiModel(value = "Input for create new book")
public class BookAddInput {

    @ApiModelProperty(value = "book name for create new book")
    private String bookName;

    @ApiModelProperty(value = "isbn for create new book")
    @NotEmpty(message="ISBN may not be empty")
    @Pattern(regexp = Constants.RegEx.NUMERIC_VALIDATION)
    private String isbn; // International Standart Book Number

    @ApiModelProperty(value = "writer for create new book")
    @NotEmpty(message="Writer may not be empty")
    private String writer;

    @ApiModelProperty(value = "existYear for create new book")
    @NotEmpty(message="ExistYear may not be empty")
    private String existYear;

    @ApiModelProperty(value = "stock for create new book")
    private Long stock;

    @ApiModelProperty(value = "price for create new book")
    private double price;
}

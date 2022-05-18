package com.getir.readingisgood.input;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ApiModel(value = "Input for create new order")
public class OrderAddInput {

    @NotNull
    @Valid
    @ApiModelProperty(value = "book detail for create new order")
    public List<OrderBookDetailInput> books = new ArrayList<>();
}

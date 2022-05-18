package com.getir.readingisgood.input;

import com.getir.readingisgood.entity.Book;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@ApiModel(value = "Model for order book list")
public class OrderBookListModel {

    @ApiModelProperty(value = "Book list model book object")
    private Book book;

    @ApiModelProperty(value = "quantity for book model")
    private Integer quantity;

    @ApiModelProperty(value = "price for book model")
    private Double price;
}

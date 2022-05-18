package com.getir.readingisgood.input;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Model for Statistics")
public class StatisticModel {

    @ApiModelProperty(value = "valueOfMount for statisctics model")
    private int valueOfMount;

    @ApiModelProperty(value = "totalOrderCount for statisctics model")
    private int totalOrderCount;

    @ApiModelProperty(value = "totalBookCount for statisctics model")
    private int totalBookCount;

    @ApiModelProperty(value = "totalAmount for statisctics model")
    private Double totalAmount;
}

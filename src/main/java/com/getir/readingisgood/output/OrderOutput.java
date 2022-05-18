package com.getir.readingisgood.output;

import com.getir.readingisgood.Enum.OrderStatus;
import com.getir.readingisgood.input.CustomerInput;
import com.getir.readingisgood.input.OrderBookListModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderOutput {

    private CustomerInput customerInput;

    private LocalDateTime orderDate;

    private OrderStatus status;

    private List<OrderBookListModel> books = new ArrayList<>();
}

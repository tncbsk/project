package com.getir.readingisgood.entity;

import com.getir.readingisgood.Enum.OrderStatus;
import com.getir.readingisgood.input.OrderBookListModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode
@Document(collection = "orders")
public class Order implements BaseEntity{

    @Id
    protected String id;

    private Customer customer;

    private LocalDate orderDate;

    private OrderStatus status;

    private List<OrderBookListModel> books = new ArrayList<>();

}

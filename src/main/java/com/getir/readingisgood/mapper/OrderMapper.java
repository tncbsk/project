package com.getir.readingisgood.mapper;

import com.getir.readingisgood.entity.Order;
import com.getir.readingisgood.output.OrderOutput;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    Order convertToOrder(OrderOutput orderOutput);

    OrderOutput converToOrderDto(Order order);

    List<Order> coverToOrder(List<OrderOutput> orderOutputList);

    List<OrderOutput> converToOrderDto(List<Order> orderlist);

}

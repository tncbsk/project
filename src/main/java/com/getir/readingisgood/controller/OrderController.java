package com.getir.readingisgood.controller;

import com.getir.readingisgood.input.OrderAddInput;
import com.getir.readingisgood.output.OrderOutput;
import com.getir.readingisgood.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
@Api(value = "Order Controller Documentation")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create-order")
    @ApiOperation(value = "Create Order Method")
    ResponseEntity<Void> createOrder(@Valid @RequestBody OrderAddInput orderAddInput) {

        orderService.createNewOrder(orderAddInput);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get Order By Id")
    public ResponseEntity<OrderOutput> getOrderByKey(@PathVariable("id") String id) {

        final OrderOutput orderResponse = orderService.getOrderById(id);

        return ResponseEntity.ok(orderResponse);
    }

    @GetMapping("/list-order-by-date")
    @ApiOperation(value = "Get Orders Between Dates")
    public ResponseEntity<List<OrderOutput>> getOrderByKey(@RequestParam String startDate, @RequestParam String endDate) {

        final List<OrderOutput> orderList = orderService.getOrderListWithDate(LocalDate.parse(startDate), LocalDate.parse(endDate));

        return ResponseEntity.ok(orderList);
    }
}

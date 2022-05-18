package com.getir.readingisgood.controller;

import com.getir.readingisgood.input.CustomerInput;
import com.getir.readingisgood.service.CustomerService;
import com.getir.readingisgood.output.OrderOutput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer")
@Api(value = "Customer Controller Documentation")
public class CustomerController {

    final private CustomerService customerService;

    @PostMapping("/register")
    @ApiOperation(value = "Register New Customer Method")
    ResponseEntity<Void> createUser(@Valid @RequestBody CustomerInput customerInput) {

        customerService.createCustomer(customerInput);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/customer-order-list")
    @ApiOperation(value = "List Customer Orders Method")
    ResponseEntity<List<OrderOutput>> customerOrderList() {

        final List<OrderOutput> customerAllOrders = customerService.getCustomerAllOrders();

        return ResponseEntity.ok(customerAllOrders);
    }

}

package com.getir.readingisgood.service;

import com.getir.readingisgood.input.CustomerInput;
import com.getir.readingisgood.repository.CustomerRepository;
import com.getir.readingisgood.entity.Customer;
import com.getir.readingisgood.exception.BusinessException;
import com.getir.readingisgood.mapper.CustomerMapper;

import com.getir.readingisgood.mapper.OrderMapper;
import com.getir.readingisgood.entity.Order;
import com.getir.readingisgood.output.OrderOutput;
import com.getir.readingisgood.repository.OrderRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    final CustomerRepository customerRepository;

    final OrderRepository orderRepository;

    public void createCustomer(CustomerInput customerInput) {

        try {
            final String email = customerInput.getEmail();
            final Optional<Customer> customerExist = getCustomer(email);

            if (customerExist.isPresent()) {

                throw new BusinessException("Customer already exist");
            }

            final Customer newCustomer = CustomerMapper.INSTANCE.convertToCustomer(customerInput);
            customerRepository.save(newCustomer);

        } catch (Exception ex) {
            throw new BusinessException(ex.getMessage());
        }

    }

    public Optional<Customer> getCustomer(String email) {

        return customerRepository.findByEmail(email);

    }

    public List<OrderOutput> getCustomerAllOrders() {

        final UserDetails userDetails =
                (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        final String email = userDetails.getUsername();

        final Optional<Customer> customerOpt = customerRepository.findByEmail(email);

        if (!customerOpt.isPresent()) {

            throw new BusinessException("Customer not found");
        }

        final Customer customer = customerOpt.get();

        final List<Order> allCustomerOrder = orderRepository.findAllByCustomer(customer, PageRequest.of(0, 10));

        final List<OrderOutput> customerOrderOutputs = OrderMapper.INSTANCE.converToOrderDto(allCustomerOrder);

        return customerOrderOutputs;
    }

}

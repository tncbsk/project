package com.getir.readingisgood.service;


import com.getir.readingisgood.Enum.OrderStatus;
import com.getir.readingisgood.entity.Customer;
import com.getir.readingisgood.entity.Order;
import com.getir.readingisgood.input.OrderAddInput;
import com.getir.readingisgood.input.OrderBookDetailInput;
import com.getir.readingisgood.input.OrderBookListModel;
import com.getir.readingisgood.mapper.OrderMapper;
import com.getir.readingisgood.output.OrderOutput;
import com.getir.readingisgood.repository.BookRepository;
import com.getir.readingisgood.repository.CustomerRepository;
import com.getir.readingisgood.repository.OrderRepository;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import org.springframework.security.test.context.support.WithMockUser;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;
import java.util.Optional;


@RunWith(MockitoJUnitRunner.Silent.class)
public class OrderServiceTest extends TestCase {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private BookRepository bookRepository;

    @Before
    public void setUp() {

        orderService = new OrderService(orderRepository, customerRepository, bookRepository);
    }

    @Test
    public void testGetOrderById() {

        String id = "62837cfe5eabe229c88d8d41";
        Order order = new Order();
        order.setOrderDate(LocalDate.now());
        order.setStatus(OrderStatus.PENDING);
        Customer customer = new Customer();
        customer.setName("Ahmet");
        customer.setSurname("hassan");
        customer.setEmail("ahmethassan@xxx.com");

        List<OrderBookListModel> books = new ArrayList<>();
        OrderBookListModel orderBookListModel = new OrderBookListModel();
        orderBookListModel.setQuantity(10);
        orderBookListModel.setPrice(100.20);
        books.add(orderBookListModel);
        order.setBooks(books);
        order.setCustomer(customer);

        Mockito.when(orderRepository.findById(id)).thenReturn(Optional.of(order));

        final OrderOutput orderOutput = OrderMapper.INSTANCE.converToOrderDto(order);

        final OrderOutput orderById = orderService.getOrderById(id);

        assertEquals(orderOutput.getStatus() ,orderById.getStatus());

    }
    @Test
    public void testGetOrderListWithDate() {

        String startDate = "2022-05-17";
        String endDate =   "2022-05-20";

        List<Order> orders = new ArrayList<>();

        Order order = new Order();
        order.setOrderDate(LocalDate.now());
        order.setStatus(OrderStatus.PENDING);
        Customer customer = new Customer();
        customer.setName("Ahmet");
        customer.setSurname("hassan");
        customer.setEmail("ahmethassan@xxx.com");

        List<OrderBookListModel> books = new ArrayList<>();
        OrderBookListModel orderBookListModel = new OrderBookListModel();
        orderBookListModel.setQuantity(10);
        orderBookListModel.setPrice(100.20);
        books.add(orderBookListModel);
        order.setBooks(books);
        order.setCustomer(customer);
        orders.add(order);

        Mockito.when(orderRepository.findByOrderDateBetween(LocalDate.parse(startDate), LocalDate.parse(endDate)))
                .thenReturn((orders));

        final List<OrderOutput> orderListWithDate = orderService.getOrderListWithDate(LocalDate.parse(startDate), LocalDate.parse(endDate));

        final OrderStatus statusFromMock = orders.stream().findFirst().get().getStatus();
        final OrderStatus statusFromService = orderListWithDate.stream().findFirst().get().getStatus();

        assertEquals(statusFromMock ,statusFromService);
    }
}
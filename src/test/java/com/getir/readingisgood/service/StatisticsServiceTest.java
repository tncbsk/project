package com.getir.readingisgood.service;

import com.getir.readingisgood.Enum.OrderStatus;
import com.getir.readingisgood.config.JwtToken;
import com.getir.readingisgood.entity.Customer;
import com.getir.readingisgood.entity.Order;
import com.getir.readingisgood.input.CustomerInput;
import com.getir.readingisgood.input.OrderBookListModel;
import com.getir.readingisgood.input.StatisticModel;
import com.getir.readingisgood.mapper.CustomerMapper;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


@RunWith(MockitoJUnitRunner.Silent.class)
public class StatisticsServiceTest extends TestCase {

    @InjectMocks
    private StatisticsService statisticsService;

    @Mock
    private  OrderRepository orderRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Before
    public void setUp()  {

        statisticsService = new StatisticsService(orderRepository ,customerRepository);
    }

    @Test
    public void testGetStatistics() {

        String startDate = "2022-05-17";
        String endDate =   "2022-05-20";

        String email = "ahmethasan@xxx.com";

        CustomerInput customerInput = new CustomerInput();
        customerInput.setName("Ahmet");
        customerInput.setSurname("Hasan");
        customerInput.setPassword("y11sad!!");
        customerInput.setEmail("ahmethasan@xxx.com");

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

        Mockito.when(customerRepository.findByEmail(email)).thenReturn(Optional.of(customer));

        Mockito.when(orderRepository.findAllOrdersByOrderedByAndDateBetween(customer , LocalDate.parse(startDate), LocalDate.parse(endDate)))
                .thenReturn((orders));

        Collection<StatisticModel> statisticModels = new ArrayList<>();
        StatisticModel statisticModel1 = new StatisticModel();
        statisticModel1.setTotalAmount(1235.11);
        statisticModel1.setTotalBookCount(10);
        statisticModel1.setValueOfMount(1);
        statisticModel1.setTotalOrderCount(20);

        StatisticModel statisticModel2 = new StatisticModel();
        statisticModel2.setTotalAmount(7232.11);
        statisticModel2.setTotalBookCount(10);
        statisticModel2.setValueOfMount(2);
        statisticModel2.setTotalOrderCount(20);

        StatisticModel statisticModel3 = new StatisticModel();
        statisticModel3.setTotalAmount(3225.11);
        statisticModel3.setTotalBookCount(20);
        statisticModel3.setValueOfMount(3);
        statisticModel3.setTotalOrderCount(20);

        StatisticModel statisticModel4 = new StatisticModel();
        statisticModel4.setTotalAmount(1235.11);
        statisticModel4.setTotalBookCount(10);
        statisticModel4.setValueOfMount(4);
        statisticModel4.setTotalOrderCount(30);

        statisticModels.add(statisticModel1);
        statisticModels.add(statisticModel2);
        statisticModels.add(statisticModel3);
        statisticModels.add(statisticModel4);

        Mockito.when(statisticsService.getStatistics(email)).thenReturn((statisticModels));
    }
}
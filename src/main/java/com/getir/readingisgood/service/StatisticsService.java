package com.getir.readingisgood.service;

import com.getir.readingisgood.entity.Customer;
import com.getir.readingisgood.repository.CustomerRepository;
import com.getir.readingisgood.entity.Order;
import com.getir.readingisgood.exception.BusinessException;
import com.getir.readingisgood.input.OrderBookListModel;
import com.getir.readingisgood.repository.OrderRepository;
import com.getir.readingisgood.input.StatisticModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
@RequiredArgsConstructor
public class StatisticsService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    public Collection<StatisticModel> getStatistics(final String email) {
        final Optional<Customer> customerOpt = customerRepository.findByEmail(email);

        if (!customerOpt.isPresent()) {

            throw new BusinessException("Customer not found");
        }
        final Customer customer = customerOpt.get();

        final LocalDate minus = LocalDate.now().plus(1, ChronoUnit.MONTHS).minus(1, ChronoUnit.YEARS);
        final LocalDate fromBeginningOfMonth = LocalDateTime.of(minus.getYear(), minus.getMonthValue(), 1, 0, 0, 0, 0).toLocalDate();
        final List<Order> orders = orderRepository.findAllOrdersByOrderedByAndDateBetween(customer, fromBeginningOfMonth, LocalDate.now());

        return groupOrders(orders);
    }

    private Collection<StatisticModel> groupOrders(final List<Order> orders) {

        final Map<Month, StatisticModel> groupedOrdersByDate = new EnumMap<>(Month.class);
        orders.forEach(order -> {
            final Month month =
                    order.getOrderDate().getMonth();
            if (groupedOrdersByDate.get(month) == null) {
                final StatisticModel statisticModel = new StatisticModel(
                        month.getValue(),
                        1,
                        order.getBooks().stream().map(OrderBookListModel::getQuantity).reduce(0, Integer::sum),
                        order.getBooks().stream().map(OrderBookListModel::getPrice).reduce(0.0, Double::sum)
                );

                groupedOrdersByDate.put(month, statisticModel);
            } else {
                final StatisticModel statisticModel = groupedOrdersByDate.get(month);
                statisticModel.setTotalOrderCount(statisticModel.getTotalOrderCount() + 1);
                statisticModel.setTotalBookCount(statisticModel.getTotalBookCount() + order.getBooks().stream().map(OrderBookListModel::getQuantity).reduce(0, Integer::sum));
                statisticModel.setTotalAmount(statisticModel.getTotalAmount() + (order.getBooks().stream().map(OrderBookListModel::getPrice).reduce(0.0, Double::sum)));
                groupedOrdersByDate.put(month, statisticModel);
            }

        });

        return groupedOrdersByDate.values();
    }
}

package com.getir.readingisgood.service;

import com.getir.readingisgood.Enum.OrderStatus;
import com.getir.readingisgood.input.OrderAddInput;
import com.getir.readingisgood.input.OrderBookDetailInput;
import com.getir.readingisgood.input.OrderBookListModel;
import com.getir.readingisgood.output.OrderOutput;
import com.getir.readingisgood.entity.Order;
import com.getir.readingisgood.exception.BusinessException;
import com.getir.readingisgood.mapper.OrderMapper;
import com.getir.readingisgood.entity.Book;
import com.getir.readingisgood.repository.BookRepository;
import com.getir.readingisgood.entity.Customer;
import com.getir.readingisgood.repository.CustomerRepository;
import com.getir.readingisgood.repository.OrderRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final CustomerRepository customerRepository;

    private final BookRepository bookRepository;

    @Transactional
    public void createNewOrder(OrderAddInput orderAddInput) {

        final UserDetails userDetails =
                (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        final String email = userDetails.getUsername();

        final Optional<Customer> customerOptional =
                customerRepository.findByEmail(email);

        if (!customerOptional.isPresent()) {

            throw new BusinessException("Customer not found");
        }

        final Order newOrder = new Order();
        newOrder.setCustomer(customerOptional.get());
        newOrder.setOrderDate(LocalDate.now());
        newOrder.setStatus(OrderStatus.PENDING);

        final List<Book> books = new ArrayList<>();
        final List<OrderBookListModel> orderBookListModel = new ArrayList<>();

        for (OrderBookDetailInput bookDetail : orderAddInput.getBooks()) {

            final String bookId = bookDetail.getBookId();
            final Integer quantity = bookDetail.getQuantity();

            final Optional<Book> bookOpt = bookRepository.findById(bookId);

            if (!bookOpt.isPresent()) {

                throw new BusinessException("Book not found");
            }

            final Book book = bookOpt.get();
            if (book.getStock() < quantity) {

                throw new BusinessException("Insufficient Stock");
            }

            final OrderBookListModel orderbook = new OrderBookListModel();
            orderbook.setBook(book);
            orderbook.setQuantity(quantity);
            orderbook.setPrice(book.getPrice() * quantity);
            orderBookListModel.add(orderbook);

            //new Stock
            book.setStock(book.getStock() - quantity);
            books.add(book);
            newOrder.setBooks(orderBookListModel);

        }
        orderRepository.save(newOrder);
        bookRepository.saveAll(books);

    }

    public OrderOutput getOrderById(String id) {

        final Optional<Order> orderDetail = orderRepository.findById(id);

        if (!orderDetail.isPresent()) {

            throw new BusinessException("Order not found");
        }

        final Order order = orderDetail.get();
        final OrderOutput orderOutput = OrderMapper.INSTANCE.converToOrderDto(order);

        return orderOutput;

    }

    public List<OrderOutput> getOrderListWithDate(LocalDate startDate, LocalDate endDate) {

        final List<Order> orderList = orderRepository.findByOrderDateBetween(startDate, endDate);

        final List<OrderOutput> orderOutputs = OrderMapper.INSTANCE.converToOrderDto(orderList);

        return orderOutputs;
    }
}

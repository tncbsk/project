package com.getir.readingisgood.repository;

import com.getir.readingisgood.entity.Customer;
import com.getir.readingisgood.entity.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

    List<Order> findByOrderDateBetween(LocalDate startDate, LocalDate endDate);

    List<Order> findAllByCustomer(Customer customer, Pageable pageable);

    @Query("{'orderedBy' : ?0, 'date' : { $gte: ?1, $lte: ?2 } }")
    List<Order> findAllOrdersByOrderedByAndDateBetween(Customer customer, LocalDate from, LocalDate to);





}

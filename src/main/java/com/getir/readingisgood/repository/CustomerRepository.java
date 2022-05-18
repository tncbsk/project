package com.getir.readingisgood.repository;

import com.getir.readingisgood.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, Long> {

    Optional<Customer> findByEmail(String email);

}

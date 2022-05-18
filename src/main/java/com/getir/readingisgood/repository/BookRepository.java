package com.getir.readingisgood.repository;

import com.getir.readingisgood.entity.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface BookRepository extends MongoRepository<Book , String> {

    Optional<Book> findByIsbn(String isbn);


}
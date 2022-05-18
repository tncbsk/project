package com.getir.readingisgood.service;


import com.getir.readingisgood.input.BookAddInput;
import com.getir.readingisgood.repository.BookRepository;
import com.getir.readingisgood.input.BookUpdateInput;
import com.getir.readingisgood.entity.Book;
import com.getir.readingisgood.exception.BusinessException;
import com.getir.readingisgood.mapper.BookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public void addBook(BookAddInput bookAddInput) {

        final String isbn = bookAddInput.getIsbn();

        final Optional<Book> book = bookRepository.findByIsbn(isbn);

        if (book.isPresent()) {

            throw new BusinessException("Book already exist");
        }

        final Book addNewBook = BookMapper.INSTANCE.convertToBook(bookAddInput);
        bookRepository.save(addNewBook);

    }

    public void updateBookStock(BookUpdateInput bookUpdateInput) {

        final String isbn = bookUpdateInput.getIsbn();
        final Optional<Book> book = bookRepository.findByIsbn(isbn);

        if (!book.isPresent()) {

            throw new BusinessException("Book not found");
        }

        final Book updateBook = book.get();
        final Long stockCount = bookUpdateInput.getStock();
        if (Objects.nonNull(stockCount)) {

            updateBook.setStock(stockCount);
        }

        final Double price = bookUpdateInput.getPrice();
        if (Objects.nonNull(price)) {

            updateBook.setPrice(price);
        }

        bookRepository.save(updateBook);
    }

}

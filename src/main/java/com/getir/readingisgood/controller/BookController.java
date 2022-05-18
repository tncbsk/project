package com.getir.readingisgood.controller;

import com.getir.readingisgood.input.BookAddInput;
import com.getir.readingisgood.service.BookService;
import com.getir.readingisgood.input.BookUpdateInput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/book")
@Api(value = "Book Controller Documentation")
public class BookController {

    final private BookService bookService;

    @PostMapping("/add-book")
    @ApiOperation(value = "Add Book Method")
    ResponseEntity<Void> addBook(@Valid @RequestBody BookAddInput bookInput) {

        bookService.addBook(bookInput);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/update-book-stock")
    @ApiOperation(value = "Update Book Method")
    ResponseEntity<Void> updateBookStock(@Valid @RequestBody BookUpdateInput bookUpdateInput) {

        bookService.updateBookStock(bookUpdateInput);

        return ResponseEntity.ok().build();
    }

}

package com.getir.readingisgood.service;

import com.getir.readingisgood.config.JwtToken;
import com.getir.readingisgood.entity.Book;
import com.getir.readingisgood.input.BookAddInput;
import com.getir.readingisgood.input.BookUpdateInput;
import com.getir.readingisgood.mapper.BookMapper;
import com.getir.readingisgood.repository.BookRepository;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.Silent.class)
public class BookServiceTest extends TestCase {

    @InjectMocks
    BookService bookService;

    @Mock
    private  BookRepository bookRepository;

    @Before
    public void setUp() {

        bookService = new BookService(bookRepository);
    }

    @Test
    public void testAddBook() {

        BookAddInput addInput = new BookAddInput();
        addInput.setIsbn("1234");
        addInput.setPrice(100.00);
        addInput.setStock(20L);
        addInput.setWriter("J.K Rowling");
        addInput.setBookName("Harry Potter");
        addInput.setExistYear("2011");

        final Book book = BookMapper.INSTANCE.convertToBook(addInput);

        bookService.addBook(addInput);

        // Then
        verify(bookRepository, Mockito.times(1)).save(book);

    }

    @Test
    public void testUpdateBookStock() {

        BookUpdateInput bookUpdateInput = new BookUpdateInput();

        bookUpdateInput.setStock(10L);
        bookUpdateInput.setIsbn("1234");
        bookUpdateInput.setPrice(100.20);

        Book book = new Book();
        book.setIsbn("1234");
        book.setPrice(100.00);
        book.setStock(20L);
        book.setWriter("J.K Rowling");
        book.setBookName("Harry Potter");
        book.setExistYear("2011");

        Mockito.when(bookRepository.findByIsbn(bookUpdateInput.getIsbn())).thenReturn(Optional.of(book));

        bookService.updateBookStock(bookUpdateInput);

        verify(bookRepository, Mockito.times(1)).findByIsbn(bookUpdateInput.getIsbn());

        verify(bookRepository, Mockito.times(1)).save(book);
    }
}
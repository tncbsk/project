package com.getir.readingisgood.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.getir.readingisgood.input.BookAddInput;
import com.getir.readingisgood.input.BookUpdateInput;
import com.getir.readingisgood.service.AuthenticationService;
import com.getir.readingisgood.service.BookService;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class BookControllerTest extends TestCase {

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();
    private ObjectWriter objectWriter = objectMapper.writer();

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }

    @Test
    public void testAddBookSuccess() throws  Exception{

        BookAddInput addInput = new BookAddInput();
        addInput.setIsbn("1234");
        addInput.setPrice(100.00);
        addInput.setStock(20L);
        addInput.setWriter("J.K Rowling");
        addInput.setBookName("Harry Potter");
        addInput.setExistYear("2011");

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/book/add-book")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectWriter.writeValueAsString(addInput)))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    public void testAddBookFail() throws  Exception{

        BookAddInput addInput = new BookAddInput();
        addInput.setIsbn("1234");
        addInput.setPrice(100.00);
        addInput.setStock(20L);
        addInput.setWriter("J.K Rowling");
        addInput.setBookName("Harry Potter");

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/book/add-book")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectWriter.writeValueAsString(addInput)))
                .andExpect(status().is4xxClientError()).andReturn();
    }

    @Test
    public void testUpdateBookStockSucess() throws Exception{

        BookUpdateInput bookUpdateInput = new BookUpdateInput();
        bookUpdateInput.setIsbn("1234");
        bookUpdateInput.setPrice(200.00);
        bookUpdateInput.setStock(120L);


        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/book/update-book-stock")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectWriter.writeValueAsString(bookUpdateInput)))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    public void testUpdateBookStockFail() throws Exception{

        BookUpdateInput bookUpdateInput = new BookUpdateInput();

        bookUpdateInput.setPrice(200.00);
        bookUpdateInput.setStock(1L);


        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/book/update-book-stock")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectWriter.writeValueAsString(bookUpdateInput)))
                .andExpect(status().is4xxClientError()).andReturn();
    }
}
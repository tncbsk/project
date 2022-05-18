package com.getir.readingisgood.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.getir.readingisgood.input.OrderAddInput;
import com.getir.readingisgood.input.OrderBookDetailInput;
import com.getir.readingisgood.service.BookService;
import com.getir.readingisgood.service.OrderService;
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

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest extends TestCase {

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();
    private ObjectWriter objectWriter = objectMapper.writer();

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
    }

    @Test
    public void testCreateOrder() throws  Exception {

        OrderAddInput orderAddInput = new OrderAddInput();
        List<OrderBookDetailInput> books = new ArrayList<>();
        OrderBookDetailInput book = new OrderBookDetailInput();
        book.setBookId("62837ce65eabe229c88d8d40");
        book.setQuantity(100);
        books.add(book);
        orderAddInput.setBooks(books);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/order/create-order")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectWriter.writeValueAsString(orderAddInput)))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    public void testGetOrderByKey() throws  Exception{

        String id = "62837cfe5eabe229c88d8d41";

        mockMvc.perform(get("/api/order/{id}",id)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    public void testTestGetOrderByKey() throws  Exception{

        String startDate = "2022-05-17";
        String endDate =   "2022-05-20";

        mockMvc.perform(get("/api/order/list-order-by-date")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .param("startDate",startDate)
                .param("endDate",endDate))
                .andExpect(status().isOk()).andReturn();
    }
}
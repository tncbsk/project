package com.getir.readingisgood.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.getir.readingisgood.input.CustomerInput;
import com.getir.readingisgood.service.BookService;
import com.getir.readingisgood.service.CustomerService;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest extends TestCase {

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();
    private ObjectWriter objectWriter = objectMapper.writer();

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void testCreateUser() throws Exception {

        CustomerInput customerInput = new CustomerInput();
        customerInput.setPassword("deneme1234");
        customerInput.setEmail("tncbsk@hotmail.com");
        customerInput.setName("tunç");
        customerInput.setSurname("baskak");

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/customer/register")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectWriter.writeValueAsString(customerInput)))
                .andExpect(status().isOk()).andReturn();

    }

    @Test
    public void testCustomerOrderList() throws Exception{


        mockMvc.perform(get("/api/customer/customer-order-list")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk()).andReturn();
    }
}
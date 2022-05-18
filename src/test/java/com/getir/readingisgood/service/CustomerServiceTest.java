package com.getir.readingisgood.service;

import com.getir.readingisgood.entity.Customer;
import com.getir.readingisgood.input.CustomerInput;
import com.getir.readingisgood.mapper.CustomerMapper;
import com.getir.readingisgood.repository.CustomerRepository;
import com.getir.readingisgood.repository.OrderRepository;
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
public class CustomerServiceTest extends TestCase {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private OrderRepository orderRepository;


    @Before
    public void setUp()  {

        customerService = new CustomerService(customerRepository ,orderRepository);
    }

    @Test
    public void testCreateCustomer() {
        CustomerInput customerInput = new CustomerInput();
        customerInput.setName("Ahmet");
        customerInput.setSurname("Hasan");
        customerInput.setPassword("y11sad!!");
        customerInput.setEmail("ahmethasan@xxx.com");

        Customer customer = CustomerMapper.INSTANCE.convertToCustomer(customerInput);

        customerService.createCustomer(customerInput);

        verify(customerRepository, Mockito.times(1)).save(customer);
    }

    @Test
    public void testGetCustomer() {

        String email = "ahmethasan@xxx.com";

        CustomerInput customerInput = new CustomerInput();
        customerInput.setName("Ahmet");
        customerInput.setSurname("Hasan");
        customerInput.setPassword("y11sad!!");
        customerInput.setEmail("ahmethasan@xxx.com");

        Customer customer = CustomerMapper.INSTANCE.convertToCustomer(customerInput);

        Mockito.when(customerRepository.findByEmail(email)).thenReturn(Optional.of(customer));
    }

}
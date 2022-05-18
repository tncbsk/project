package com.getir.readingisgood.service;

import com.getir.readingisgood.config.JwtToken;
import com.getir.readingisgood.entity.Customer;
import com.getir.readingisgood.exception.BusinessException;
import com.getir.readingisgood.input.AuthenticationInput;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import org.springframework.security.core.userdetails.UserDetails;


import java.util.Optional;


@RunWith(MockitoJUnitRunner.Silent.class)
public class AuthenticationServiceTest extends TestCase {

    @InjectMocks
    AuthenticationService authenticationService;

    @Mock
    private CustomerService customerService;

    @Mock
    private  JwtToken jwtToken;

    @Before
    public void setUp()  {

        authenticationService = new AuthenticationService(customerService ,jwtToken);
    }

    @Test
    public void testCheckCustomerPasswordAndCreateTokenSucess() {

        AuthenticationInput authenticationInput = new AuthenticationInput();
        authenticationInput.setEmail("mrvshn@xxx.com");
        authenticationInput.setPassword("deneme123");

        Customer customer = new Customer();
        customer.setName("Merve");
        customer.setSurname("Baskak");
        customer.setPassword("deneme123");

        final String email = authenticationInput.getEmail();

        Mockito.when(customerService.getCustomer(email)).thenReturn(Optional.of(customer));

        Mockito.when(authenticationService.checkCustomerPasswordAndCreateToken(authenticationInput)).thenThrow(BusinessException.class);

    }

    @Test
    public void testLoadUserByUsername() {

        String email = "mrvshn@xxx.com";

        Customer customer = new Customer();
        customer.setName("Merve");
        customer.setSurname("Baskak");
        customer.setPassword("deneme123");

        Mockito.when(customerService.getCustomer(email)).thenReturn(Optional.of(customer));

        final UserDetails userDetailsReturn = authenticationService.loadUserByUsername(email);

        Assert.assertEquals(userDetailsReturn.getUsername() , email);

    }
}
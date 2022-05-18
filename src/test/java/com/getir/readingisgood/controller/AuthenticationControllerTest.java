package com.getir.readingisgood.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.getir.readingisgood.input.AuthenticationInput;
import com.getir.readingisgood.service.AuthenticationService;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticationControllerTest extends TestCase {

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();
    private ObjectWriter objectWriter = objectMapper.writer();

   @Mock
   private AuthenticationService authenticationService;

   @InjectMocks
   private AuthenticationController authenticationController;

   @Before
   public void setUp() {
       MockitoAnnotations.initMocks(this);
       this.mockMvc = MockMvcBuilders.standaloneSetup(authenticationController).build();
   }

    @Test
    public void testCreateTokenFail() throws Exception {

        AuthenticationInput authenticationInput = new AuthenticationInput();
        authenticationInput.setPassword("getir12345");
        authenticationInput.setEmail("getir");

        mockMvc.perform(MockMvcRequestBuilders
                .post("/authenticate/create-token")
                .content(objectWriter.writeValueAsString(authenticationInput)))
                .andExpect(status().is4xxClientError()).andReturn();

    }

    @Test
    public void testCreateTokenSucess() throws Exception {

        AuthenticationInput authenticationInput = new AuthenticationInput();
        authenticationInput.setPassword("deneme123");
        authenticationInput.setEmail("mrvshn@xxx.com");

        mockMvc.perform(MockMvcRequestBuilders
                .post("/authenticate/create-token")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectWriter.writeValueAsString(authenticationInput)))
                .andExpect(status().isOk()).andReturn();;

    }
}
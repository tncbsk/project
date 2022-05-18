package com.getir.readingisgood.controller;

import com.getir.readingisgood.input.AuthenticationInput;
import com.getir.readingisgood.service.AuthenticationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping(value = "/authenticate")
@Api(value = "Authentication Controller Documentation")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/create-token")
    @ApiOperation(value = "Create Token Method", notes = "Create token only register customer")
    public ResponseEntity<Object> createToken(@Valid @RequestBody AuthenticationInput authenticationInput) throws Exception {

        final String token = authenticationService.checkCustomerPasswordAndCreateToken(authenticationInput);

        return ResponseEntity.ok(token);
    }
}

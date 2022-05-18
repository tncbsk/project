package com.getir.readingisgood.service;

import com.getir.readingisgood.input.AuthenticationInput;
import com.getir.readingisgood.config.JwtToken;
import com.getir.readingisgood.entity.Customer;
import com.getir.readingisgood.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements UserDetailsService {

    private final CustomerService customerService;

    private final JwtToken jwtToken;

    public String checkCustomerPasswordAndCreateToken(AuthenticationInput authenticationInput) {

        try {
            final String email = authenticationInput.getEmail();
            final String password = authenticationInput.getPassword();
            final Optional<Customer> customer = customerService.getCustomer(email);

            if (!customer.isPresent() || !customer.get().getPassword().equals(password)) {

                throw new UsernameNotFoundException("User not found");
            }

            final UserDetails userDetails = loadUserByUsername(email);
            return jwtToken.generateToken(userDetails);

        } catch (Exception ex) {
            throw new BusinessException("Token Can Not Create");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        final Optional<Customer> customerOpt = customerService.getCustomer(email);

        User.UserBuilder builder = null;

        if (customerOpt.isPresent()) {
            final Customer customer = customerOpt.get();

            builder = User.withUsername(email);
            builder.password(new BCryptPasswordEncoder().encode(customer.getPassword()));
            builder.authorities(new ArrayList<>());
        } else
            throw new UsernameNotFoundException("User not found");

        return builder.build();
    }

}

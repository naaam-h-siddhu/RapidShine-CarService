package com.rapidshine.carwash.carservice.service;

import com.rapidshine.carwash.carservice.exceptions.UserNotFoundException;
import com.rapidshine.carwash.carservice.model.Customer;
import com.rapidshine.carwash.carservice.model.User;
import com.rapidshine.carwash.carservice.repository.CustomerRepository;
import com.rapidshine.carwash.carservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() ->  new UserNotFoundException("USer not " +
                "found "));
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword() !=null ?
                user.getPassword(): "",new ArrayList<>());
    }
}

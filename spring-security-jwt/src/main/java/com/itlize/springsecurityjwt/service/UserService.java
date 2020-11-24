package com.itlize.springsecurityjwt.service;

import com.itlize.springsecurityjwt.models.AuthenticationRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface UserService extends UserDetailsService {
    public AuthenticationRequest findOneByNamee(String name);

    List<AuthenticationRequest> findAll();

    public AuthenticationRequest save(AuthenticationRequest authenticationRequest);
}

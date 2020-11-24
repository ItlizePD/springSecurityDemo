package com.itlize.springsecurityjwt.service.serviceImpl;

import com.itlize.springsecurityjwt.DAO.UserDao;
import com.itlize.springsecurityjwt.models.AuthenticationRequest;
import com.itlize.springsecurityjwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.NameNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    UserDao userDao;

    @Override
    public AuthenticationRequest findOneByNamee(String name) {
        return userDao.findById(name).orElse(null);
    }

    @Override
    public List<AuthenticationRequest> findAll() {
        return userDao.findAll();
    }


    @Override
    public AuthenticationRequest save(AuthenticationRequest authenticationRequest) {
        return userDao.save(authenticationRequest);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<AuthenticationRequest> user = userDao.findById(s);
        System.out.println(user);
        if (user == null) {
            throw new UsernameNotFoundException("Usernot find by the username");
        }else{
            return new org.springframework.security.
                    core.userdetails.User(user.get().getUsername(),
                    passwordEncoder.encode(user.get().getPassword()), new ArrayList<>());
        }
    }
}

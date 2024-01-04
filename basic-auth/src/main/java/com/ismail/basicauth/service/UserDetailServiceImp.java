package com.ismail.basicauth.service;

import com.ismail.basicauth.model.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailServiceImp implements UserDetailsService {

    private final UserService userService;

    public UserDetailServiceImp(UserService userService) {
        this.userService = userService;

    }




    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = userService.getByUsername(username);
        return user.orElseThrow(
                EntityNotFoundException::new);
    }
}

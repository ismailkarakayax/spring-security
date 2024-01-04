package com.ismail.jwttoken.controller;

import ch.qos.logback.classic.Logger;
import com.ismail.jwttoken.dto.AuthRequest;
import com.ismail.jwttoken.dto.CreateUserRequest;
import com.ismail.jwttoken.model.User;
import com.ismail.jwttoken.service.JwtService;
import com.ismail.jwttoken.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;
    private Logger log;

    public UserController(UserService userService, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }


    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }

    @PostMapping("/addNewUser")
    public User createUser(@RequestBody CreateUserRequest request) {

        return userService.createUser(request);
    }

    @PostMapping("/generateToken")
    public String generateToken(@RequestBody AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );
        if(authentication.isAuthenticated()) {
            return jwtService.generateToken(request.username());
        }
        log.info("Invalid username or password"+request.username());
        throw new RuntimeException("Invalid username or password"+request.username());
    }
    @GetMapping("/user")
    public String getUserString() {
        return "This is USER!";
    }

    @GetMapping("/admin")
    public String getAdminString() {
        return "This is ADMIN!";
    }

}

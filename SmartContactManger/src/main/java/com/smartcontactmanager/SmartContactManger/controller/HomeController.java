package com.smartcontactmanager.SmartContactManger.controller;

import com.smartcontactmanager.SmartContactManger.entity.User;

import com.smartcontactmanager.SmartContactManger.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class HomeController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user, BindingResult result) {
        // Check if validation errors exist
        if (result.hasErrors()) {
            // Create an empty list to store error messages
            List<String> errors = new ArrayList<>();

            // Loop through all errors in BindingResult and extract default error message
            result.getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));

            // Return HTTP BAD_REQUEST status with the error messages in response
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }

        boolean isRegistered = userService.registerUser(user);
        if (isRegistered) {
            return ResponseEntity.ok("User registered successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User registration failed!");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody User user) {
        String email = user.getEmail();
        String password = user.getPassword();
        User foundUser = userService.loginUser(email, password);

        if (foundUser != null) {
            return ResponseEntity.ok(foundUser);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }


}

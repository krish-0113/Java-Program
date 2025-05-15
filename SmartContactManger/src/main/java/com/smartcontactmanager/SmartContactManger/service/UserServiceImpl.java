package com.smartcontactmanager.SmartContactManger.service;

import com.smartcontactmanager.SmartContactManger.entity.User;
import com.smartcontactmanager.SmartContactManger.repository.UserRepository;
import com.smartcontactmanager.SmartContactManger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public boolean registerUser(User user) {
        try {
            // ✅ Encode password before saving
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public User loginUser(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);  // Get user by email
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // ✅ Compare raw password with hashed password
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;  // Return user if password matches
            }
        }
        return null;  // Return null if user is not found or password doesn't match
    }
}

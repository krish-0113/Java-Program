package com.smartcontactmanager.SmartContactManger.conifg;

import com.smartcontactmanager.SmartContactManger.entity.User;
import com.smartcontactmanager.SmartContactManger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class UserDetailsServiceImple implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // fetching user from database using email or username
        Optional<User> user = userRepository.findByEmail(username);
        // If user is not found, throw UsernameNotFoundException
        if (!user.isPresent()) {
            throw new UsernameNotFoundException("Could not find user with username: " + username);
        }
      //user ka data database se fetch karte ho aur CustomUserDetails mein convert kar ke return karte h0
        // Convert user to CustomUserDetails and return
        return new CustomUserDetaills(user.get());
    }
}

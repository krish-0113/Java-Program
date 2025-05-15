package com.smartcontactmanager.SmartContactManger.conifg;

import com.smartcontactmanager.SmartContactManger.conifg.UserDetailsServiceImple;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

    // UserDetailsService se user ka data fetch hoga
    @Bean
    public UserDetailsService getUserDetailsService() {
        return new UserDetailsServiceImple();
    }

    // Password encode karne ke liye BCrypt use ho raha hai
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



    // DaoAuthenticationProvider se authentication configure ho raha hai
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        //user details kaha se fetch karni hai
        daoAuthenticationProvider.setUserDetailsService(this.getUserDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        //> Spring Security isko use karega authentication ke liye
        return daoAuthenticationProvider;
    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/admin/**").hasRole("ADMIN")  // Only ADMIN can access /admin/**
//                        .requestMatchers("/user/**").hasRole("USER")   // Only USER can access /user/**
//                        .anyRequest().authenticated()  // Any other request needs authentication
//                )
//                .formLogin(login -> login
//                        .loginPage("/login")  // Custom login page (optional)
//                        .defaultSuccessUrl("/dashboard", true)  // Redirect after successful login
//                        .permitAll()  // Allow everyone to access login page
//                )
//                .logout(logout -> logout
//                        .logoutUrl("/logout")
//                        .logoutSuccessUrl("/login?logout")  // Redirect after logout
//                        .permitAll()
//                );
//
//        return http.build();
//       }
//
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF protection (useful for testing with Postman)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/users/signup", "/api/users/login").permitAll() // Allow public access to signup & login endpoints
                        .anyRequest().authenticated()  // All other requests require authentication
                )
                .formLogin(form -> form
                        .loginPage("/login") // Specifies a custom login page at "/login"
                        .loginProcessingUrl("/perform_login") // Endpoint where the login form will be submitted
                        .defaultSuccessUrl("/home", true) // Redirect to "/home" after successful login
                        .failureUrl("/login?error=true") // Redirect to "/login" with an error message if login fails
                        .permitAll() // Allow everyone to access the login page
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // Specifies the URL to trigger logout
                        .logoutSuccessUrl("/login?logout=true") // Redirects to "/login" with a logout message after successful logout
                        .invalidateHttpSession(true) // Destroys the user's session after logout
                        .deleteCookies("JSESSIONID") // Deletes the session cookie (important for stateless authentication)
                )
                .httpBasic(Customizer.withDefaults()); // Enables basic authentication (for API access)

        return http.build(); // Builds and returns the SecurityFilterChain
    }



}




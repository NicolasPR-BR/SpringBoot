package com.code.springboot.mvcSecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class DemoSecurityConfig {
    // In memory database, just so we have some data to play with
    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
            UserDetails john = User.builder()
                    .username("john")
                    .password("{noop}test123") //Password has no encryption, so noop "no operation"
                    .roles("EMPLOYEE") //John only has 1 role, and it's employee
                    .build();

            UserDetails mary = User.builder()
                    .username("mary")
                    .password("{noop}test123")
                    .roles("EMPLOYEE", "MANAGER") //Mary's also a manager, so she has "MANAGER"
                    .build();

            UserDetails susan = User.builder()
                    .username("susan")
                    .password("{noop}test123")
                    .roles("EMPLOYEE", "MANAGER", "ADMIN") //Susan's also an ADMIN
                    .build();

        return new InMemoryUserDetailsManager(john, mary, susan);
    }
}
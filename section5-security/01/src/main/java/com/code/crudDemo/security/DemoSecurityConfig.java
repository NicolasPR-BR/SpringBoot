package com.code.crudDemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class DemoSecurityConfig {

       @Bean
        public InMemoryUserDetailsManager userDetailsManager(){
           //{noop} plain-text
           UserDetails john = User.builder().username("root").password("{noop}root").roles("EMPLOYEE").build();
           UserDetails mary = User.builder().username("mary").password("{noop}mary").roles("EMPLOYEE", "MANAGER").build();
           UserDetails susan = User.builder().username("susan").password("{noop}susan").roles("EMPLOYEE", "MANAGER", "ADMIN").build();
           return new InMemoryUserDetailsManager(john, mary, susan);
       }

}
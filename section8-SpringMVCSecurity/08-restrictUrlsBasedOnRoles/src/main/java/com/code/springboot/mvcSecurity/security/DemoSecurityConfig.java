package com.code.springboot.mvcSecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

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

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Restrict access based on the HTTP request
        http.authorizeHttpRequests(configurer ->
                // Says that any request MUST BE authenticated to be able to access the content
                configurer
                        .requestMatchers("/").hasRole("EMPLOYEE")
                        .requestMatchers("/leaders/**").hasRole("MANAGER")
                        .requestMatchers("/systems/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
        ).formLogin(form ->
                //FormLogin is where the form can be configured
            form.loginPage("/showMyLoginPage") //Tells spring where the login page is
                    .loginProcessingUrl("/authenticateUser") //Where the form data is submitted to be checked Spring boot provides this code for us
                    .permitAll() //Says that anyone should be able to access this resource, because everyone should be able to login
        ).logout(logout -> logout.permitAll()); //Allows anyone to access "/logout"

        return http.build();
    }
}
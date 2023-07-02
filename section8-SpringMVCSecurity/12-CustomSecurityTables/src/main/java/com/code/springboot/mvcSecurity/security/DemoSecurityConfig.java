package com.code.springboot.mvcSecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class DemoSecurityConfig {
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        //Retrieves user by username
        jdbcUserDetailsManager.setUsersByUsernameQuery("SELECT user_id, pw, active FROM members WHERE user_id=?");

        //Define query to retrieve the authorities by username
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("SELECT user_id, role FROM roles WHERE user_id=?");

        return jdbcUserDetailsManager;
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
        ).logout(logout -> logout.permitAll()) //Allows anyone to access "/logout"
                .exceptionHandling(configurer ->
                        // Access denied page
                        configurer.accessDeniedPage("/access-denied"));

        return http.build();
    }
}
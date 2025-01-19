package com.librarydaegu.demo.encryption;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class LibrarySecurityConfig {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {

        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);

        // set user's email(for id) and password
        userDetailsManager.setUsersByUsernameQuery(
                "select email, password from renter where email=?");

        return userDetailsManager;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.formLogin(
                formLogin ->
                        formLogin.loginPage("/")
                                .loginProcessingUrl("/authenticateTheUser")
                                .permitAll()

        );

        return http.build();
    }
}

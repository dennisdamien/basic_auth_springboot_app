package com.tst.basicauth.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@AllArgsConstructor
public class SecurityConfig {
    private BCryptPasswordEncoder passwordEncoder;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.DELETE).hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT,"/admin/users/*/approve").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/admin/users/approved").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/admin/users").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"user/*/setunamepass").hasRole("USER")
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .httpBasic();
        return http.build();
    }

    @Bean
    public UserDetailsService users(){


            UserDetails admin= User.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("adminpass"))
                    .roles("ADMIN")
                    .build();
            UserDetails user = User.builder()
                    .username("user")
                    .password(passwordEncoder.encode("userpass"))
                    .roles("USER")
                    .build();
            return new InMemoryUserDetailsManager(admin,user);
        }
    }


package com.tst.basicauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableCaching
public class BasicAuthApplication {

	public static void main(String[] args) {

		SpringApplication.run(BasicAuthApplication.class,args);
	}
	@Bean
	public BCryptPasswordEncoder passwordEncoder(){
		return  new BCryptPasswordEncoder();
	}

}

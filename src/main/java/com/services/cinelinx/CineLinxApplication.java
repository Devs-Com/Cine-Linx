package com.services.cinelinx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.thymeleaf.dialect.springdata.SpringDataDialect;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CineLinxApplication {

	public static void main(String[] args) {
		SpringApplication.run(CineLinxApplication.class, args);
	}

	@Bean
    public SpringDataDialect springDataDialect() {
        return new SpringDataDialect();
	}
}

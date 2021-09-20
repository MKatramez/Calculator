package com.example.calculator;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CalculatorConfig {

    @Bean
    CommandLineRunner commandLineRunner(CalculatorRepository repository) {
        return args -> {

        };
    }
}

package com.example.calculator;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CalculatorException extends RuntimeException {

    public CalculatorException() {
        super("Syntax Error");
    }
}
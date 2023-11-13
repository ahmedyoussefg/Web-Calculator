package com.jetbrains.agoerany.simplecalculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

@SpringBootApplication
public class SimpleCalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleCalculatorApplication.class, args);
	}

}

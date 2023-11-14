package com.jetbrains.agoerany.simplecalculator;

import org.springframework.expression.EvaluationException;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParseException;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
public class Control {

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping(value = "/calculate/")
    public String evaluateExpression(@RequestParam String expression){
        ExpressionParser expressionParser = new SpelExpressionParser();
        System.out.println("Expression: " + expression);
        try {
            String fixedExpression = fixExpression(expression); // fix expression
            System.out.println("Processed Expression: " + expression);
            Expression expr = expressionParser.parseExpression(fixedExpression);

            Double value;
            try {
                value = expr.getValue(Double.class);
            } catch (ArithmeticException e){
                return "E";
            }
            System.out.println(value);
            if (value==null){
                return "E";
            }
            if (value.isInfinite() || value.isNaN()){
                return "E";
            }

            return value.toString();
        } catch(ParseException | EvaluationException e) {
            System.out.println(e.getMessage());
            return "E";
        }
    }

    /**
     * to fix division, the parser evaluates non-floating numbers as integers (like 3/2=1)
     * @param expr : the expression
     * @return fixed expression
     */
    public static String fixExpression(String expr) {
        String[] splitted = expr.split("[/]");
        for (int i = 0; i < splitted.length; i ++) {
            boolean found_point = false;
            String curr = splitted[i];
            for (int j = splitted[i].length() - 1; j >= 0; j--) {
                char ch = splitted[i].charAt(j);
                if (ch == '+' || ch == '*' || ch == '-' || ch == '^') break;
                if (ch == '.') {
                    found_point = true;
                    break;
                }
            }
            if (!found_point) splitted[i] += ".0";
        }
        String new_expr = "";
        for (int i = 0; i < splitted.length - 1; i++) {
            new_expr += splitted[i] + "/";
        }
        new_expr += splitted[splitted.length - 1];
        return new_expr;
    }
}

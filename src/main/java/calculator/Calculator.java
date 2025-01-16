package calculator;

import parser.Parser;
import validator.Validator;

import java.util.Arrays;

public class Calculator {
    private final Parser parser;
    private final Validator validator;

    public static final int THRESHOLD = 1001;

    public Calculator(Parser parser, Validator validator) {
        this.parser = parser;
        this.validator = validator;
    }

    public int add(final String numbers) {
        final String[] operands = parser.parseOperands(numbers);

        validator.validate(operands);

        return Arrays.stream(operands)
                .filter(operand -> !operand.isEmpty())
                .mapToInt(Integer::parseInt)
                .filter(operand -> operand < THRESHOLD)
                .sum();
    }
}
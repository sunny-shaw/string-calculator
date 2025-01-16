package calculator;

import parser.Parser;

import java.util.Arrays;

public class Calculator {
    private final Parser parser;
    private static final String DEFAULT_DELIMITER = ",";
    public static final String EMPTY_STRING = "";
    public static final String VALIDATION_ERROR_MESSAGE = "negatives not allowed: ";
    public static final int THRESHOLD = 1001;

    public Calculator(Parser parser) {
        this.parser = parser;
    }

    public int add(final String numbers) {
        final String[] operands = parser.parseOperands(numbers);

        validate(operands);

        return Arrays.stream(operands)
                .filter(operand -> !operand.isEmpty())
                .mapToInt(Integer::parseInt)
                .filter(operand -> operand < THRESHOLD)
                .sum();
    }

    private static void validate(String[] operands) {
        final String negativeNumbers = Arrays.stream(operands)
                .filter(operand -> !operand.isEmpty())
                .mapToInt(Integer::parseInt)
                .filter(n -> n < 0)
                .mapToObj(String::valueOf)
                .reduce((n1, n2) -> n1 + DEFAULT_DELIMITER + n2)
                .orElse(EMPTY_STRING);

        if (!negativeNumbers.isEmpty()) {
            throw new IllegalArgumentException(VALIDATION_ERROR_MESSAGE + negativeNumbers);
        }
    }
}
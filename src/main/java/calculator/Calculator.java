package calculator;

import java.util.Arrays;
import java.util.Objects;

public class Calculator {
    public static final String DELIMITER = ",";
    public static final String EMPTY_STRING = "";

    public int add(String numbers) {
        String[] parsedOperands = numbers.split(DELIMITER);

        return Arrays.stream(parsedOperands)
                .filter(operand -> !Objects.equals(operand, EMPTY_STRING))
                .mapToInt(Integer::parseInt)
                .sum();
    }
}
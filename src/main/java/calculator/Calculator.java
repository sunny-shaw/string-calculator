package calculator;

import java.util.Objects;

public class Calculator {
    public static final String DELIMITER = ",";

    public int add(String numbers) {
        if (numbers.contains(DELIMITER)) {
            String[] parsedOperands = numbers.split(DELIMITER);
            return Integer.parseInt(parsedOperands[0]) + Integer.parseInt(parsedOperands[1]);
        } else if (!Objects.equals(numbers, "")) {
            return Integer.parseInt(numbers);
        } else {
            return 0;
        }
    }
}
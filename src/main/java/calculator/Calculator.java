package calculator;

import java.util.Arrays;

public class Calculator {
    private static final String DEFAULT_DELIMITER = ",";
    private static final String CUSTOM_DELIMITER_PREFIX = "//";
    private static final String LINE_BREAK = "\n";

    public int add(final String numbers) {
        final String[] operands = parseOperands(numbers);
        return Arrays.stream(operands)
                .filter(operand -> !operand.isEmpty())
                .mapToInt(Integer::parseInt)
                .sum();
    }

    private static String[] parseOperands(final String numbers) {
        String delimiter = DEFAULT_DELIMITER;
        String extractedNumbers = numbers;

        if (extractedNumbers.startsWith(CUSTOM_DELIMITER_PREFIX)) {
            int delimiterEnd = extractedNumbers.indexOf(LINE_BREAK);
            delimiter = extractedNumbers.substring(2, delimiterEnd);
            extractedNumbers = extractedNumbers.substring(delimiterEnd + 1);
        }

        return extractedNumbers
                .replace(LINE_BREAK, delimiter)
                .split(delimiter);
    }
}
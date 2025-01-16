package calculator;

import java.util.Arrays;

public class Calculator {
    private static final String DEFAULT_DELIMITER = ",";
    private static final String CUSTOM_DELIMITER_PREFIX = "//";
    private static final String LINE_BREAK = "\n";
    public static final String EMPTY_STRING = "";
    public static final String VALIDATION_ERROR_MESSAGE = "negatives not allowed: ";
    public static final int THRESHOLD = 1001;
    public static final String DELIMITER_PREFIX = "[";
    public static final String DELIMITER_SUFFIX = "]";

    public int add(final String numbers) {
        final String[] operands = parseOperands(numbers);

        validate(operands);

        return Arrays.stream(operands)
                .filter(operand -> !operand.isEmpty())
                .mapToInt(Integer::parseInt)
                .filter(operand -> operand < THRESHOLD)
                .sum();
    }

    private static String[] parseOperands(final String numbers) {
        String delimiter = DEFAULT_DELIMITER;
        String extractedNumbers = numbers;

        if (extractedNumbers.startsWith(CUSTOM_DELIMITER_PREFIX)) {
            int delimiterEnd = extractedNumbers.indexOf(LINE_BREAK);
            String delimiterDefinition = extractedNumbers.substring(2, delimiterEnd);

            if (delimiterDefinition.startsWith(DELIMITER_PREFIX) && delimiterDefinition.endsWith(DELIMITER_SUFFIX)) {
                delimiter = delimiterDefinition.substring(1, delimiterDefinition.length() - 1);
            } else {
                delimiter = delimiterDefinition;
            }

            extractedNumbers = extractedNumbers.substring(delimiterEnd + 1);
        }

        final String escapedDelimiter = escapeForRegex(delimiter);

        return extractedNumbers
                .replace(LINE_BREAK, delimiter)
                .split(escapedDelimiter);
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

    private static String escapeForRegex(String delimiter) {
        return delimiter.replaceAll("(\\W)", "\\\\$1");
    }
}
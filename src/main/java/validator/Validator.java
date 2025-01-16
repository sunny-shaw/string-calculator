package validator;

import java.util.Arrays;

public class Validator {
    private static final String DEFAULT_DELIMITER = ",";
    public static final String EMPTY_STRING = "";
    public static final String VALIDATION_ERROR_MESSAGE = "negatives not allowed: ";

    public void validate(String[] operands) {
        final String negativeNumbers = Arrays.stream(operands)
                .filter(operand -> !operand.isEmpty())
                .mapToInt(Integer::parseInt)
                .filter(operand -> operand < 0)
                .mapToObj(String::valueOf)
                .reduce((n1, n2) -> n1 + DEFAULT_DELIMITER + n2)
                .orElse(EMPTY_STRING);

        if (!negativeNumbers.isEmpty()) {
            throw new IllegalArgumentException(VALIDATION_ERROR_MESSAGE + negativeNumbers);
        }
    }
}
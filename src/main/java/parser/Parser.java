package parser;

import java.util.Arrays;

public class Parser {
    private static final String DEFAULT_DELIMITER = ",";
    private static final String CUSTOM_DELIMITER_PREFIX = "//";
    private static final String LINE_BREAK = "\n";
    public static final String DELIMITER_PREFIX = "[";
    public static final String DELIMITER_SUFFIX = "]";
    public static final String PIPE = "|";

    public String[] parseOperands(final String numbers) {
        String extractedNumbers = numbers;
        String delimiterPattern = DEFAULT_DELIMITER;

        if (extractedNumbers.startsWith(CUSTOM_DELIMITER_PREFIX)) {
            int delimiterEnd = extractedNumbers.indexOf(LINE_BREAK);
            final String delimiterDefinition = extractedNumbers.substring(2, delimiterEnd);

            if (delimiterDefinition.startsWith(DELIMITER_PREFIX) && delimiterDefinition.endsWith(DELIMITER_SUFFIX)) {
                delimiterPattern = extractMultipleDelimiters(delimiterDefinition);
            } else {
                delimiterPattern = escapeForRegex(delimiterDefinition);
            }

            extractedNumbers = extractedNumbers.substring(delimiterEnd + 1);
        }

        delimiterPattern = delimiterPattern + PIPE + LINE_BREAK;

        return extractedNumbers.split(delimiterPattern);
    }

    private static String extractMultipleDelimiters(String delimiterDefinition) {
        final String[] delimiters = delimiterDefinition.substring(1, delimiterDefinition.length() - 1).split("]\\[");

        return Arrays.stream(delimiters)
                .map(Parser::escapeForRegex)
                .reduce((d1, d2) -> d1 + PIPE + d2)
                .orElse(DEFAULT_DELIMITER);
    }

    private static String escapeForRegex(String delimiter) {
        return delimiter.replaceAll("(\\W)", "\\\\$1");
    }
}
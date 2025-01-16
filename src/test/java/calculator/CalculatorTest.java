package calculator;

import org.junit.jupiter.api.Test;
import parser.Parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorTest {
    private final Parser parser = new Parser();
    private final Calculator calculator = new Calculator(parser);

    @Test
    void shouldReturnZeroForEmptyString() {
        int actual = calculator.add("");

        assertEquals(0, actual);
    }

    @Test
    void shouldReturnSameNumberForSingleNumberInput() {
        int actual = calculator.add("1");

        assertEquals(1, actual);
    }

    @Test
    void shouldReturnSumForTwoNumbersInput() {
        int actual = calculator.add("1,5");

        assertEquals(6, actual);
    }

    @Test
    void shouldReturnSumForAnyAmountOfNumbersInput() {
        int actual = calculator.add("1,5,4,6,7,9");

        assertEquals(32, actual);
    }

    @Test
    void shouldHandleNewLinesInBetweenNumbersInput() {
        int actual = calculator.add("1\n2,3");

        assertEquals(6, actual);
    }

    @Test
    void shouldSupportDifferentDelimiters() {
        int actual = calculator.add("//;\n1;2");

        assertEquals(3, actual);
    }

    @Test
    void shouldThrowExceptionWhenNegativeNumberFound() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> calculator.add("//;\n-1;-2;5"));

        assertEquals("negatives not allowed: -1,-2", exception.getMessage());
    }

    @Test
    void numbersBiggerThan1000ShouldBeIgnored() {
        int actual = calculator.add("//;\n1001;2");

        assertEquals(2, actual);
    }

    @Test
    void shouldAllowVariableLengthDelimiter() {
        int actual = calculator.add("//[***]\n1***2***3");

        assertEquals(6, actual);
    }

    @Test
    void shouldAllowMultipleDelimiter() {
        int actual = calculator.add("//[*][%]\n1*2%3");

        assertEquals(6, actual);
    }

    @Test
    void shouldAllowMultipleDelimiterWithVariableLength() {
        int actual = calculator.add("//[***][%%]\n1***2%%3");

        assertEquals(6, actual);
    }
}
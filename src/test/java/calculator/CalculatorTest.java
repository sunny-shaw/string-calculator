package calculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {
    @Test
    void   shouldReturnZeroForEmptyString() {
        Calculator calculator = new Calculator();

        int actual = calculator.add("");

        assertEquals(0, actual);
    }

    @Test
    void shouldReturnSameNumberForSingleNumberInput() {
        Calculator calculator = new Calculator();

        int actual = calculator.add("1");

        assertEquals(1, actual);
    }
}

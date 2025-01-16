package calculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {
    private final Calculator calculator = new Calculator();
    
    @Test
    void   shouldReturnZeroForEmptyString() {
        int actual = calculator.add("");

        assertEquals(0, actual);
    }

    @Test
    void shouldReturnSameNumberForSingleNumberInput() {
        int actual = calculator.add("1");

        assertEquals(1, actual);
    }
}

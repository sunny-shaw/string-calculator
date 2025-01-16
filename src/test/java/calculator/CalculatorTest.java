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
}

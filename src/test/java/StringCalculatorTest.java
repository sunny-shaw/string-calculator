import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringCalculatorTest {
    @Test
    void verifyTestSetup() {
        String actual = "Hello, World!";

        assertEquals("Hello, World!", actual);
    }
}

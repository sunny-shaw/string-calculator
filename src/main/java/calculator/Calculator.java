package calculator;

import java.util.Objects;

public class Calculator {
    public int add(String numbers) {
        if(!Objects.equals(numbers, "")) {
            return Integer.parseInt(numbers);
        } else {
            return 0;
        }
    }
}
package calculator;

import parser.Parser;
import validator.Validator;

import java.util.HashMap;
import java.util.Map;

public class Calculator {
    private final Parser parser;
    private final Validator validator;
    private static final int THRESHOLD = 1001;
    private static final int CUBIC_THRESHOLD = 3;

    public Calculator(Parser parser, Validator validator) {
        this.parser = parser;
        this.validator = validator;
    }

    public int add(final String numbers) {
        String[] operands = parser.parseOperands(numbers);
        validator.validate(operands);
        Map<Integer, Integer> frequencyMap = getFrequencyMap(operands);
        return calculateSum(frequencyMap);
    }

    private Map<Integer, Integer> getFrequencyMap(String[] operands) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (String operand : operands) {
            if (!operand.isEmpty()) {
                int number = Integer.parseInt(operand);
                if (number < THRESHOLD) {
                    freqMap.put(number, freqMap.getOrDefault(number, 0) + 1);
                }
            }
        }
        return freqMap;
    }

    private int calculateSum(Map<Integer, Integer> frequencyMap) {
        int sum = 0;
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            int value = entry.getKey();
            int count = entry.getValue();
            sum += (count < CUBIC_THRESHOLD) ? (value * count) : (int) Math.pow(value, 3);
        }
        return sum;
    }
}
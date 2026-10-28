package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CalculatorTest {

    private final Calculator calculator = new Calculator();

    @Test
    void testAdd() {
        Assertions.assertEquals(8, calculator.add(5, 3));
    }

    @Test
    void testSubtract() {
        Assertions.assertEquals(2.0, calculator.subtract(5, 3));
    }

    @Test
    void testMultiply() {
        Assertions.assertEquals(15.0, calculator.multiply(5, 3));
    }

    @Test
    void testDivide() {
        Assertions.assertEquals(5.0, calculator.divide(10, 2));
    }

    @Test
    void testDivisionByZero() {
        Assertions.assertThrows(ArithmeticException.class, () -> calculator.divide(10, 0));
    }

    @Test
    void testInvalidInput() {
        Assertions.assertThrows(InvalidInputException.class, () -> calculator.add(Double.NaN, 5));
    }

    @Test
    void testInvalidStringInput() {
        Assertions.assertThrows(NumberFormatException.class, () -> Double.parseDouble("abc"));
    }
}
package org.example;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Calculator calculator = new Calculator();

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Введіть перше число: ");
            double a = Double.parseDouble(scanner.nextLine());

            System.out.print("Введіть друге число: ");
            double b = Double.parseDouble(scanner.nextLine());

            System.out.print("Введіть операцію (+, -, *, /): ");
            String operation = scanner.nextLine();

            double result = switch (operation) {
                case "+" -> calculator.add(a, b);
                case "-" -> calculator.subtract(a, b);
                case "*" -> calculator.multiply(a, b);
                case "/" -> calculator.divide(a, b);
                default -> throw new InvalidInputException("Невідома операція");
            };

            System.out.println("Результат: " + result);

        } catch (NumberFormatException e) {
            System.out.println("Помилка: потрібно вводити числа");
        } catch (ArithmeticException | InvalidInputException e) {
            System.out.println("Помилка: " + e.getMessage());
        } finally {
            System.out.println("Обробку запиту завершено");
        }
    }
}
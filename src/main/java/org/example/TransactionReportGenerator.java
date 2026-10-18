package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public abstract class TransactionReportGenerator {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static void printBalanceReport(double totalBalance) {
        System.out.println("Загальний баланс: " + totalBalance);
    }

    public static void printTransactionsCountByMonth(String monthYear, int count) {
        System.out.println("Кількість транзакцій за " + monthYear + ": " + count);
    }

    public static void printTopExpensesReport(List<Transaction> topExpenses) {
        System.out.println("10 найбільших витрат:");
        for (Transaction expense : topExpenses) {
            System.out.println(expense.getDescription() + ": " + expense.getAmount());
        }
    }

    public static void printMinMaxExpensesReport(Transaction[] expenses) {
        System.out.println("Найбільша витрата: " + expenses[0].getDescription() + ": " + expenses[0].getAmount());
        System.out.println("Найменша витрата: " + expenses[1].getDescription() + ": " + expenses[1].getAmount());
    }

    public static void printExpensesTextReport(List<Transaction> transactions) {
        List<String> processedCategories = new ArrayList<>();
        List<String> processedMonths = new ArrayList<>();

        System.out.println("\nВитрати по категоріях (1 * = 1000 грн):");

        for (Transaction transaction : transactions) {
            if (transaction.getAmount() < 0 && !processedCategories.contains(transaction.getDescription())) {
                double total = 0;

                for (Transaction t : transactions) {
                    if (t.getAmount() < 0 && t.getDescription().equals(transaction.getDescription())) {
                        total += -t.getAmount();
                    }
                }

                processedCategories.add(transaction.getDescription());
                System.out.println(transaction.getDescription() + ": " + total + " грн " + "*".repeat((int) (total / 1000)));
            }
        }

        System.out.println("\nВитрати по місяцях (1 * = 1000 грн):");

        for (Transaction transaction : transactions) {
            LocalDate date = LocalDate.parse(transaction.getDate(), DATE_FORMATTER);
            String month = date.format(DateTimeFormatter.ofPattern("MM-yyyy"));

            if (transaction.getAmount() < 0 && !processedMonths.contains(month)) {
                double total = 0;

                for (Transaction t : transactions) {
                    LocalDate transactionDate = LocalDate.parse(t.getDate(), DATE_FORMATTER);
                    String transactionMonth = transactionDate.format(DateTimeFormatter.ofPattern("MM-yyyy"));

                    if (t.getAmount() < 0 && transactionMonth.equals(month)) {
                        total += -t.getAmount();
                    }
                }

                processedMonths.add(month);
                System.out.println(month + ": " + total + " грн " + "*".repeat((int) (total / 1000)));
            }
        }
    }
}
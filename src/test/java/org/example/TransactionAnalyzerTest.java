package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class TransactionAnalyzerTest {

    @Test
    public void testCalculateTotalBalance() {
        Transaction transaction1 = new Transaction("01-01-2023", 100.0, "Дохід");
        Transaction transaction2 = new Transaction("02-01-2023", -50.0, "Витрата");
        Transaction transaction3 = new Transaction("03-01-2023", 150.0, "Дохід");

        List<Transaction> transactions = Arrays.asList(transaction1, transaction2, transaction3);

        double result = TransactionAnalyzer.calculateTotalBalance(transactions);

        Assertions.assertEquals(200.0, result, "Розрахунок загального балансу неправильний");
    }

    @Test
    public void testCountTransactionsByMonth() {
        Transaction transaction1 = new Transaction("01-02-2023", 50.0, "Дохід");
        Transaction transaction2 = new Transaction("15-02-2023", -20.0, "Витрата");
        Transaction transaction3 = new Transaction("05-03-2023", 100.0, "Дохід");

        List<Transaction> transactions = Arrays.asList(transaction1, transaction2, transaction3);

        int countFeb = TransactionAnalyzer.countTransactionsByMonth(transactions, "02-2023");
        int countMar = TransactionAnalyzer.countTransactionsByMonth(transactions, "03-2023");

        Assertions.assertEquals(2, countFeb, "Кількість транзакцій за лютий неправильна");
        Assertions.assertEquals(1, countMar, "Кількість транзакцій за березень неправильна");
    }

    @Test
    public void testFindTopExpenses() {
        List<Transaction> transactions = List.of(
                new Transaction("01-01-2023", -100, "1"),
                new Transaction("02-01-2023", -200, "2"),
                new Transaction("03-01-2023", -300, "3"),
                new Transaction("04-01-2023", -400, "4"),
                new Transaction("05-01-2023", -500, "5"),
                new Transaction("06-01-2023", -600, "6"),
                new Transaction("07-01-2023", -700, "7"),
                new Transaction("08-01-2023", -800, "8"),
                new Transaction("09-01-2023", -900, "9"),
                new Transaction("10-01-2023", -1000, "10"),
                new Transaction("11-01-2023", -1100, "11"),
                new Transaction("12-01-2023", 5000, "Дохід")
        );

        List<Transaction> topExpenses = TransactionAnalyzer.findTopExpenses(transactions);

        Assertions.assertEquals(10, topExpenses.size());
        Assertions.assertEquals(-1100.0, topExpenses.get(0).getAmount());
        Assertions.assertEquals(-200.0, topExpenses.get(9).getAmount());
    }
}
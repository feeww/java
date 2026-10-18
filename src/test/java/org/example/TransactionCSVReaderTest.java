package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class TransactionCSVReaderTest {

    @Test
    public void testReadTransactions() {
        String filePath = "src/main/resources/pr2.csv";

        List<Transaction> transactions = TransactionCSVReader.readTransactions(filePath);

        Assertions.assertFalse(transactions.isEmpty());
        Assertions.assertEquals("05-12-2023", transactions.get(0).getDate());
        Assertions.assertEquals(-450.0, transactions.get(0).getAmount());
        Assertions.assertEquals("Сільпо", transactions.get(0).getDescription());
    }
}
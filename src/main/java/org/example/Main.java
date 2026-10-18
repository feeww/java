package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String filePath = "src/main/resources/pr2.csv";

        List<Transaction> transactions = TransactionCSVReader.readTransactions(filePath);

//        for (Transaction transaction : transactions) {
//            System.out.println(transaction);
//        }
//
//        double totalBalance = TransactionAnalyzer.calculateTotalBalance(transactions);
//
//        String monthYear = "01-2024";
//        int transactionsCount = TransactionAnalyzer.countTransactionsByMonth(transactions, monthYear);
//
//        List<Transaction> topExpenses = TransactionAnalyzer.findTopExpenses(transactions);
//
//        TransactionReportGenerator.printBalanceReport(totalBalance);
//        TransactionReportGenerator.printTransactionsCountByMonth(monthYear, transactionsCount);
//        TransactionReportGenerator.printTopExpensesReport(topExpenses);

        String startDate = "01-12-2023";
        String endDate = "31-12-2023";

        Transaction[] minMaxExpenses = TransactionAnalyzer.findMinMaxExpensesInPeriod(transactions, startDate, endDate);

        TransactionReportGenerator.printMinMaxExpensesReport(minMaxExpenses);
        TransactionReportGenerator.printExpensesTextReport(transactions);
    }
}